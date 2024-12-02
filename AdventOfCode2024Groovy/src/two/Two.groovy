package two

def p1() {
    long r = 0;
    def a = []
    def b = []
    new File("input.txt").eachLine {
        a << it.split(/\s+/).collect{it as int}
    }

    r = a.count{s->
        safe(s)
    }

    println r
}

def safe(r) {
    def diff = (1..<r.size()).collect {r[it] - r[it - 1]}

    if (!diff.every {it.abs() in 1..3}) return false

    def inc = diff.every { it > 0 }
    def dec = diff.every { it < 0 }

    return inc || dec
}

def safe2(r) {
    if (safe(r)) return true

    for (int i = 0; i < r.size(); ) {
        def rr = r[0..<i, ++i..<r.size()]
        if (safe(rr)) return true
    }

    return false
}

def p2() {
    long r = 0;
    def a = []
    def b = []
    new File("input.txt").eachLine {
        a << it.split(/\s+/).collect{it as int}
    }

    r = a.count{s->
        safe2(s)
    }

    println r
}

p1()
p2()
