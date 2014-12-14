#!/usr/bin/env bash
# Usage: monitor_branch.sh [branch] [repo] [query_every_ms] [dot_every_ms]
# Set up parameter defaults
branch=${1:-`git rev-parse --abbrev-ref HEAD`}
repo=${2:-innodonni/DonniBot}
query_every_ms=${3:-2000}
dot_every_ms=${4:-500}

build_status() {
	travis history -r "$repo" -b "$branch" --limit 1 2>/dev/null |
	cut -d":" -f1
}
pid_exists() {
	kill -0 $1 >/dev/null 2>&1
}
get_status_with_callback() {
  build_status | $1
}
clear_screen() {
  echo -e "\033[2J"
}
print_output() {
  # Await EOF from standard input
  build_status=$(clear_screen && cat)
  echo -n $build_status
}

# Grab sleep supporting decimal argument from same directory (not in Git for Windows)
CWD=$(dirname "${BASH_SOURCE[0]}")
PATH=${CWD}:$PATH

query_took=0
while true; do
	get_status_with_callback print_output &
	pid=$!
	slept=$query_took
	query_took=0
	while pid_exists $pid || [ $slept -lt $query_every_ms ]; do
		sleep 0.2 && slept=$(expr $slept + 200)
		pid_exists $pid && query_took=$(expr $query_took + 200)
		[ $(expr $slept % $dot_every_ms) -eq 0 ] && echo -n .
	done
	# wait for pid in case it took longer than $query_every_ms
	wait $pid 2>/dev/null
done