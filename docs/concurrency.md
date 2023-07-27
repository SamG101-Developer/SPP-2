# Concurrency & Parallelism
## Concurrency
- Functions used for concurrency are marked as `gn` rather than `fn`
- Functions marked `gn` return either `std::Gen[T]` or `std::Fut[T]` (see below)

### Coroutines
#### Overview
- Generators are first-class and stackless.
- Asymmetric -> one coroutine controls the other => semi-coroutines.
- Coroutines aren't defined with `fn`, but `gn`
  - Because the coroutines are asymmetric, it can be argued they are only semi-coroutines => generators.
  - Generator `gn` functions must return a type super-imposing either `std::Gen[T]` or `std::Fut[T]`.

#### Calling
- The `std::Gen[T]` / `std::Fut[T]` is returned immediately, and the generator is not started.
- The generator is started when the `next(...)` function is called on the `std::Gen[T]` object.
- Using the parameter allows values to be passed back to the coroutine (`let x = yield 123`)
- Every `std::Gen[T]::next()` yields a `std::Ret[T, std::GenErr]` object, which contains either the next value or an error.
  - The `std::Gen[T]::next()` function can be called multiple times, and the generator will continue to run until it is finished.
  - Returns an error when the generator is finished.

#### Yielding values from inside the generator
- Use the `yield` keyword to yield a value from inside the generator.
- Each `yield`'s expression's type must match, and be the `T` of the `std::Gen[T]` return type.
- Use a `let` statement to get the value passed into `next` from the caller for this yield.

### Async-Await
#### Overview
- There is no `await` or `async` keyword or syntax, because coroutines do the same thing.
- Instead of returning `std::Gen[T]` from the `gn`, return `std::Fut[T]`.
- The `await` keyword is just a blocking op on the next generator value, i.e. call `std::Fut[T]::wait()` and wait.
- This means that `async` isn't needed -- just define `gn` functions and call `wait()` on them.

## Parallelism
### Threads & Mutexes
#### Threads
- Threads are created with the `std::thread::spawn()` function.
- Threads are joined with the `std::thread::join()` function, but auto-join by default.

#### Mutexes
- Mutexes are created with the `std::mutex::new(T)` function.
- Mutexes are locked with the `std::mutex::lock()` function.

### Channels
