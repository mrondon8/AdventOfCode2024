package three

def solve() {
    def a = new File("input.txt").collect()
    println([p1(a), p2(a)])
}

def p1(a) {
    (a =~ /mul\((\d+{1,3}),(\d{1,3})\)/).collect{m,x,y->
        (x as int) * (y as int)
    }.sum()
}

def p2(a) {
    def e = 1
    (a =~ /mul\((\d+{1,3}),(\d{1,3})\)|(do|don't)\(\)/).collect {m,x,y,f->
        e = f == "do" ? 1 : f == "don't" ? 0 : e
        e && x ? (x as int) * (y as int) : 0
    }.sum()
}

solve()
