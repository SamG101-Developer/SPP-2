# Memory safety
### Ownership
- Owned object: `A`
- Borrowed object: `&A` or `&mut A`
- Partial moves: moving a field from an owned object, leaving it partially moved.
  - Partially moved objects are heavily restricted in applicable operations to them.

#### Laws of exclusivity - a value must be one of:
1. An owned object (not borrowed)
2. Borrowed immutably (n immutable borrows can occur simultaneously)
3. Borrowed mutably (only 1 mutable borrow can occur at a time)
- Mutable and immutable borrows cannot exist at the same time for a given object.

### Common issues mitigated
#### Null safety
- No concept of "pointers", as they are abstracted away into owned objects and "borrows".
- Residual-holding types - `Opt[T]` and `Ret[T]` - are used to represent the possibility of a value not existing.
- Ownership tracking ensures that uninitialized or moved objects aren't used.

#### Buffer overflows
- All low-level memory access is bound checked in the `MemSeq[T]` class (from `Alloc[T]`).
- Bound checks can result in errors -> return `Ret[T, MemErr]` type.
- Storing the length of the sequence in the object allows for bounds checking to be performed.

#### Use after free & Double free
- Ownership tracking ensures that objects are not used after they are "freed" (moved).
- Double free is the same as "use after free" so it is also prevented.

#### Memory leaks
- All objects are uniquely-owned => falls out of scope, then it is automatically freed after destructor is called.
- Memory leaks cannot occur, as the object will automatically be de-allocated upon destruction.
- Unique objects are always allowed to automatically fall out of scope, as second-class references ensure no active references will be active when the object is freed.

#### Data race freedom
- Use shared ownership to allow for multiple threads to access the same object.
- Mutexes unlock shared objects to only 1 is every used at one time.

### Solutions
#### Residual-holding types - `Opt[T]` and `Ret[T]`
- `Opt[T]` is used to represent the possibility of a value not existing.
- `Ret[T]` is used to represent the possibility of an error occurring.
- Reduces the need to defensively check for null values.

#### Second-class references
- References can only be taken at a call-site, and received at a function parameter.
- References can be "yielded" from coroutines, as the following `Gen[T]::next` will invalidate the reference.

#### Region based memory management
- Owned objects that fall out of scope are automatically freed.
- Guarantees "leak freedom", as there is now way for an object to stay in scope

#### Ownership tracking
- Ownership tracking ensures that objects are not used after they are "freed" (moved).
- Tracks when an object has been "moved" or not initialized on definition.
- Ensures that only valid objects are moved / taken a reference from.

