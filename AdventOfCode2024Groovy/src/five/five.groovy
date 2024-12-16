package five

def solve() {
    def a = new File("input.txt").collect()
    println([p1(a), p2(a)])
}

def p1(a) {
    Map m = [:].withDefault {[]}
    int i = 0
    for(String e : a){
        i++
        if(e.isEmpty())
            break
        def(String k, String v) = e.split(/\|/)
        m[k] << v
    }

    def goodOrders = []
    for(String e; e = a[i]; i++) {
        List<String> numbers = e.split(",")
        List<String> seen = []
        if(numbers.every{
            seen << it
            //println "$it ${m[it]} $seen"
            !m[it].any{it in seen}
        })
            goodOrders << numbers
    }
    goodOrders.sum{
        it[it.size()>>1] as int
    }
}

def p2(a) {
    Map m = [:].withDefault {[]}
    int i = 0
    for(String e : a){
        i++
        if(e.isEmpty())
            break
        def(String k, String v) = e.split(/\|/)
        m[k] << v
    }

    def badOrders = []
    for(String e; e = a[i]; i++) {
        List<String> numbers = e.split(",")
        List<String> seen = []
        if(!numbers.every{
            seen << it
            //println "$it ${m[it]} $seen"
            !m[it].any{it in seen}
        })
            badOrders << numbers
    }
    println badOrders

    badOrders = badOrders.collect{badOrder->
        println "This is a bad order $badOrder"
        def newOrder = []
        for(int j = 0; j < badOrder.size(); j++) {
            def value = badOrder[j]
            println "This value $value should go before any of these ${m[value]} let's check if the new badOrder has any of these values"
            def indexWeWantToGoIn = 1e9
            m[value].each{
                if(it in newOrder) {
                    println "Found a value that $value should go infront of $it"
                    indexWeWantToGoIn = [indexWeWantToGoIn, newOrder.indexOf(it)].min()
                }
            }
            if(indexWeWantToGoIn == 1e9) {
                newOrder << value
            } else {
                newOrder.add(indexWeWantToGoIn, value)
            }

        }
        println "The new badOrder order is $newOrder"
        newOrder
    }

    badOrders.sum{
        it[it.size()>>1] as int
    }
}

solve()
