# Unified condition expression [Not compiler-implemented]

## Comparison types
### Different comparisons
#### Member access
```s++
if x {
    .is_empty() => { std::io::println("empty"); }
    .contains("a") => { std::io::println("contains a"); }
}
```

#### Comparisons (single operation per each branch)
```s++
if x == {
    0 => { std::io::println("== 0"); }
    1 => { std::io::println("== 1"); }
}
```
```s++
if x > {
    10 => { std::io::println("> 10"); }
    00 => { std::io::println("> 00"); }
}
```

#### Comparisons (different operation per each branch)
```s++
if x {
    == 00 => { std::io::println("== 00"); }
    >= 10 => { std::io::println(">= 10"); }
}
```

#### Bindings
```s++
if x == {
    Point { x: 0, y: 0 } => { std::io::println("origin"); }
    Point { x: 0, y    } => { std::io::println("x-axis ${y}"); }
    Point { x   , y: 0 } => { std::io::println("y-axis ${x}"); }
    Point { x   , y    } => { std::io::println("(${x}, ${y})"); }
};
```
```s++
if x == {
    (0, 0) => { std::io::println("origin"); }
    (0, y) => { std::io::println("x-axis ${y}"); }
    (x, 0) => { std::io::println("y-axis ${x}"); }
    (x, y) => { std::io::println("(${x}, ${y})"); }
};
```
- This tuple works because it is the literal that maps to the `std::Tup` type.
- The `==` comparison for cases compare attributes recursively, with a binding compared as `*`.

#### Skip multiple values
```s++
if x == {
    Point { x: 0, y: 0 } => { std::io::println("origin"); }
    Point { x: 0, ... } => { std::io::println("x-axis ${x}"); }
    Point { y: 0, ... } => { std::io::println("y-axis ${y}"); }
    Point { x, y, ... } => { std::io::println("(${x}, ${y})"); }
```
```s++
let x = (0, 0, "pos_1");
if x == {
    (0, 0, ...meta_data) => { std::io::println("origin"); }
    (0, y, ...meta_data) => { std::io::println("x-axis {}", y); }
    (x, 0, ...meta_data) => { std::io::println("y-axis {}", x); }
    (x, y, ...meta_data) => { std::io::println("({}, {})", x, y); }
};
```
- The `...` can be used to skip multiple values in a tuple.
- The items in the `...` can be bound to by using something like `...other` => will be a tuple.
- Cannot bind to extra members against `...` in an initialization if pattern.

#### Combining patterns
```s++
if x == {
    1 | 2 => { std::io::println("1 or 2"); }
    3 | 4 => { std::io::println("3 or 4"); }
};
```

#### Range matching -> comparison + guard
```s++
if x > {
    00 && x < 10 => { std::io::println("01-09"); }
    10 && x < 20 => { std::io::println("10-19"); }
    20 && x < 30 => { std::io::println("20-29"); }
};
```

#### Value guards
```s++
if p {
    == Point{x: 0, y: 0} && p.method() => { std::io::println("one or two"); }
};
```
- Use a `&&` to introduce guards.

### Composing multiple patterns
- All patterns have a precedence, and can be combined into advanced patterns.
