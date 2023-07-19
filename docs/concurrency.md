# Concurrency
## Coroutines & Generators
- Coroutines are "full" -> stateful and first-class
- Asymmetric -> one coroutine controls the other => semi-coroutines
- Are the same as "generators" because of their semi-coroutine nature

### Generator definition
```s++
# Standard function
pub fn foo(a: std::Num) -> std::Str {
    return a.to_str();
}

# Generator
pub gn foo(a: std::Num) -> std::Gen[std::Str] {
    yield a.to_str();
}
```

### Generator operations
#### Create
- Call the generator as if it were a normal function
- Returns the `std::Gen[T]` object
- Call as `let gen = foo(5);`

#### Next
- Member function of `std::Gen[T]`
- Returns a `std::Ret[T, std::GenErr]` object, because the generator could be dead
- Call as `let x = gen.next();`

#### Yield
- Keyword that yields a value from inside the generator function
- Can be called multiple times from inside the generator function
- Call as `yield val;`


## Async-Await
- Instead of returning `std::Gen[T]` from the `gn`, return `std::Fut[T]`
- There is no `await` or `async` keyword or syntax, because coroutines do the same thing.
- The `await` keyword is just a blocking op on the next generator value, i.e. call `std::Fut[T]::wait()` and wait.
- This means that `async` isn't needed -- just define `gn` functions and call `wait()` on them.
- Example of using `std::Fut[T]`:
```s++
pub gn download_data(url: std::Str) -> std::Fut[std::Str] {
    let data = http::download(url);
    return data;
}

pub fn main() {
    let data = download_data("https://example.com").wait();
    println(data);
}
```
- Note
  - There are no `yeild` statements, only a single return statement.
  - The `download_data` is a `gn`, because the `std::Fut[T]` has to be returned instantly.

---

# Parallelism
## Parallelism
- Parallelism is the ability to run multiple tasks at the same time.
- Parallelism is achieved by running multiple threads.

### Threads
- Threads are OS-level constructs that run code in parallel.


### Mutexes
### Channels
