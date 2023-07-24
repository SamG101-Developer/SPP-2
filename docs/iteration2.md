# Iteration
## Issue with iteration, indexing and slicing
- Iteration with second-class references poses a problem: references cannot be returned.
- Mitigated with coroutines that temporarily yield references, and are resumed by the iterator.
- Yielded references only live until they are resumed, ensuring memory safety.
- Provides temporary access to the current element(s) to the caller, but not to the entire collection.

## Coroutines
- Coroutines can yield references, because they have to be resumed, thereby ensuring the memory safety.
- Coroutines are denoted by `gn` rather than `fn`, telling the compiler that the return value must be immediately#
  returned, like an `std::Gen[T]` or an `std::Fut[T]`.
- Use a coroutine to yield a reference to the current element, and resume the iterator.

### Example: indexing
1. `A` calls `B(&x)`, passing a second-class `&X` reference into `B`.
2. `B` can yield `&x.y` to `A`, yielding a second-class `&Num` reference.
3. `A` can use the yielded reference, but only until `B` is resumed.
   - Resuming `B` invalidates the `&x.y` reference
   - Ends the lifetime of the yielded reference `&x.y`
4. `B` returns to `A`

### Defining iteration-based coroutines