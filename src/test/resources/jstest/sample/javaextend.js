var Predicate = Java.type('java.util.function.Predicate');
var PredicateFirst = Java.extend(Predicate, {
    test: function(val) {
        return val > 0 ? true : false;
    }
});

print(new PredicateFirst().test(1));
print(new Predicate(){
	test: function(val) {
        return val > 0 ? true : false;
    }
}.test(-1));
