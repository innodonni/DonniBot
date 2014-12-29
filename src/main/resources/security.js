(function() {
    var unsafePrint = print;
    var unsafeJava = Java;
    var keywords = ["print",
    "arguments","__FILE__","__LINE__","__DIR__",
    "eval","load","loadWithNewGlobal",
    "Packages","com","edu","java","javafx","javax","org","JavaImporter",
    "exit","quit","JSAdapter"];
    var safeMembers = ["safeMembers","Math","JSON",
    "Infinity","undefined","isFinite","isNaN","NaN",
    "parseFloat","parseInt",
    "decodeURI","decodeURIComponent","encodeURI","encodeURIComponent","unescape","escape",
    "Date","String","Boolean","Array","Number","RegExp","Object","Function",
    "Error","EvalError","RangeError","ReferenceError","SyntaxError","TypeError","URIError",
    "ArrayBuffer","DataView","Int8Array","Uint8Array","Uint8ClampedArray","Int16Array","Uint16Array","Int32Array","Uint32Array","Float32Array","Float64Array"];
    var safeClasses = [];//["java.lang.Thread"];

    var matchDuplicates = function(elem,idx,arr) { return arr.indexOf(elem) >= idx; };
    var matchEmpty = function(elem) { return elem.length == 0; };
    var head = function(array) { return array[0] };
    var tail = function(array) { return Array.prototype.slice.call(array).shift(); };
    var splitClass = function(fullClassName) { return fullClassName.split(".") };
    var arrClasses = safeClasses.map(splitClass);
    function explode(package, classes) {
        var tlPackageNames = classes.map(head).filter(matchEmpty).filter(matchDuplicates);
        if (tlPackageNames.length == 0) return package;
        var fakePackageObj = {};
        tlPackageNames.forEach(function(tlPackageName) {
            var realPackageObj = package[tlPackageName];
            print (tlPackageName+" obj="+realPackageObj);
            if (realPackageObj == null) return;
            var elements = classes.filter(function(arrClass) { return tlPackageName == head(arrClass); });
            var subElements = elements.map(tail);
            fakePackageObj[tlPackageName] = explode(realPackageObj, subElements);
        });
        return fakePackageObj;
    };
    var classHeir = explode(this, arrClasses);

//    safeMembers.forEach(function(keyword){
//        if (!(keyword in this)) {
//            print ("Not a global:" + keyword);
//        }
//    });

    var safeGlobal = new JSAdapter() {
        properties: {},

        __get__: function(name) {
            unsafePrint("getter called for '" + name + "'");
            if (name in this.properties)
                return this.properties[name];
            if (name == "java")
                return classHeir[name];
            return null;
        },

        __put__: function(name, value) {
            unsafePrint("setter called for '" + name + "' with value '"+value+"'");
            this.properties[name] = value;
        },

        __call__: function(name) {
            if (name == "print" || name == "unsafePrint" || name == "JSON")
            {
                var args = Array.prototype.slice.call(arguments);
                args.shift();
                return unsafePrint(args);
            }
        },

        __new__: function() {
            unsafePrint("new called");
        },

        __getIds__: function() {
            unsafePrint("__getIds__ called");
            return [ "foo", "bar" ];
        },

        __getValues__: function() {
            unsafePrint("__getValues__ called");
            return [ "fooval", "barval" ];
        },

        __has__: function(name) {
            unsafePrint("__has__ called with '" + name + "'");
            //return name == "print" || name == "unsafe" || name == "java" || name == "JSON";
            return true;
        },

        __delete__: function(name) {
            unsafePrint("__delete__ called with '" + name + "'");
            return true;
        },

        __noSuchProperty__: function(n) {
            unsafePrint("No such property: " + n);
        },

        __noSuchMethod__: function(n) {
            unsafePrint("No such method: " + n);
            for (i in arguments) {
                print(arguments[i])
            }
        }
    };

    // this doesn't work with JSAdaptor
    //Object.bindProperties(this, safeGlobal);

    keywords.forEach(function(keyword){
        if (keyword in this) {
//            if (!(keyword in safeMembers)) {
                //this[keyword] = safeGlobal[keyword];
                delete this[keyword];
//            }
        }
    });

    Java = {
        type: function(fullClassName) {
            for (var i in safeClasses)
                if (safeClasses[i] == fullClassName)
                    return unsafeJava.type(fullClassName);
            throw "Unsafe class: " + fullClassName;
        },
        extend: unsafeJava.extend
    };
})();