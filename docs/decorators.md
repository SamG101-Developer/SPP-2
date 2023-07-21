# Decorators / Annotations
#### What can be decorated?
- Module definition
- Function definitions
- Class definitions
- Class attributes
- Sup methods
- Sup typedefs

#### Compile-time vs Run-time decorators
- The compile-time decorators, in the `@meta` namespace, are used to mutate the AST prior to code generation.
- Other decorators wrap function calls / class instantiations, mutating runtime objects.

## Defining a decorator (THIS WILL CHANGE)
### Defining a decorator for a function
- Changes `my_method` to `decorator1(&my_method, 123)`
- Add functionality before and after the function is called
```s++
fn decorator1[F: std::FunRef](func: &F, a: std::Num) -> F::Output:
    std::io::println("decorator1 called - before function");
    let val = func();
    std::io::println("decorator1 called - after function");
    ret val + a;

@decorator1(123),
fn my_method(a: std::Num) -> std::Num:
    ret a + 1;
```

### Defining chained decorators for a function
- Changes `my_method` to `decorator1(decorator2(&my_method, 456), 123)`
- Add functionality before and after the function is called
```s++
fn decorator1[F: std::FunRef](func: &F, a: std::Num) -> F::return_type:
    std::io::println("decorator1 called - before function");
    let val = func();
    std::io::println("decorator1 called - after function");
    return val + a;

fn decorator2[F: std::FunRef](func: &F, a: std::Num) -> F::return_type:
    std::io::println("decorator2 called - before function");
    let val = func();
    std::io::println("decorator2 called - after function");
    return val + a;

@decorator1(123), @decorator2(456),
@meta::public
fn my_method(self: &self_t, a: std::Num) -> std::Num:
    return a + 1;
```

### Defining a decorator for a class
- Changes `Foo{attr: 1}` to `decorator1(Foo{attr: 1}, 123)`
- Accept an already created class, and add functionality after the class is created
- For a normal class, the decorator will return the instance of the class it received
```s++
fn decorator1[T](class: T, a: std::Num) -> T:
    class.attr = a;
    std::io::println("decorator1 called");
    return class;
    
@decorator1(123
cls Foo:
    attr: std::Num;
```

