package one

def p1() {
    long r = 0
    def a = []
    def b = []
    new File("input.txt").eachLine {
        def(x, y) = it.split(/\s+/)
        a << (x as int)
        b << (y as int)
    }

    a.sort()
    b.sort()

    (0..<a.size()).each{i->
        r += Math.abs(a[i] - b[i])
    }

    println r
}

def p2() {
    long r = 0
    def a = []
    def b = [:]
    new File("input.txt").eachLine {
        def(x, y) = it.split(/\s+/)
        a << (x as int)
        y = y as int
        b[y] = (b[y]?:0) + 1
    }

    r = a.sum{it * (b[it]?:0)}

    println r
}

p1()
p2()
