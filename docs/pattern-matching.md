# Unified condition expression [Not fully compiler-implemented]

## Comparison types
### Different comparisons
#### Member access
```s++
match x {
    .is_empty() => { std::io::println("empty"); }
    .contains("a") => { std::io::println("contains a"); }
    _ => { std::io::println("other"); }
}
```

#### Bindings
```s++
match x {
    Point { x: 0, y: 0 } => { std::io::println("origin"); }
    Point { x: 0, y } => { std::io::println("x-axis {}", y); }
    Point { x, y: 0 } => { std::io::println("y-axis {}", x); }
    Point { x, y } => { std::io::println("({}, {})", x, y); }
};
```
```s++
match x {
    (0, 0) => { std::io::println("origin"); }
    (0, y) => { std::io::println("x-axis {}", y); }
    (x, 0) => { std::io::println("y-axis {}", x); }
    (x, y) => { std::io::println("({}, {})", x, y); }
};
```
- This tuple works because it is the literal that maps to the `std::Tup` type.

#### Skip multiple values
```s++
match x {
    Point { x: 0, y: 0 } => { std::io::println("origin"); }
    Point { x: 0, ... } => { std::io::println("x-axis {}", x); }
    Point { y: 0, ... } => { std::io::println("y-axis {}", y); }
    Point { x, y, ... } => { std::io::println("({}, {})", x, y); }
```
```s++
let x = (0, 0, "pos_1");
match x {
    (0, 0, ...meta_data) => { std::io::println("origin"); }
    (0, y, ...meta_data) => { std::io::println("x-axis {}", y); }
    (x, 0, ...meta_data) => { std::io::println("y-axis {}", x); }
    (x, y, ...meta_data) => { std::io::println("({}, {})", x, y); }
};
```
- The `...` can be used to skip multiple values in a tuple.
- The items in the `...` can be bound to by using something like `...other` => will be a tuple.
- Cannot bind to the `...` extra members in an initialization match pattern.

#### Combining patterns
```s++
match x {
    1 | 2 => { std::io::println("one or two"); }
    _ => { std::io::println("other"); }
};
```

#### Range matching
```s++
match x {
    1..5 => { std::io::println("one to five"); }
    _ => { std::io::println("other"); }
};
```

#### Value guards
```s++
match x {
    x if x > 0 => { std::io::println("one or two"); }
    _ => { std::io::println("other"); }
};
```

### Composing multiple patterns
- All patterns have a precedence, and can be combined into advanced patterns.
- Example

```s++
let x = (1, 2, 3);
match x {
    (1 | 2, 3 | 4, 5 | 6) => { std::io::println("one or two, three or four, five or six"); }
    (..., 8 | 9) => { std::io::println("last element is eight or nine"); }
    _ => { std::io::println("other"); }
};
```
