# Memory safety
- Memory safety is a core feature of the language.
- Compile time memory checks are ran to prevent any runtime memory issues.

## Ownership
- Every object is uniquely owned
- Borrows to objects can only occur at function call sites
- Allows for a simple lifetime & borrow-checking model

### Borrows
- Cannot be returned
- Cannot be stored in a data structure
- Can only be created at function call sites
  - `let a = foo(b)` is a move
  - `let a = foo(&b);` is an immutable borrow
  - `let a = foo(&mut b);` is a mutable borrow
- Can only be received by a function parameter
  - `fun foo(a: A) -> Void {...}` receives an owned object
  - `fun foo(a: &A) -> Void {...}` receives an immutable borrow
  - `fun foo(a: &mut A) -> Void {...}` receives a mutable borrow

### Borrow checker rules to enforce safety
- The value being borrowed must be owned, not uninitialized or moved.
- Only 1 mutable borrow can exist at a time for a given object.
- Mutable and immutable borrows cannot exist at the same time for a given object.

### When can a borrow be taken?
- A borrow can be taken from an owned object at a function call site.
- A borrow can be taken from a borrow at a function call site (collapses to a borrow of owned object)
- A borrow cannot be taken from a partially moved object.

### Lifetime analysis
- Lifetime analysis isn't ever required, because borrows can only be active for a lifetime < the owner
- The only check is to make sure the owned object being borrowed isn't moved / uninitialized.

### Partially moved objects
- If some fields from an owned object are moved, then the object is in a "partially moved" state.
- Borrows cannot be taken from partially moved objects because it's unsafe to do so.
- Partially moved objects can have their missing fields re-assigned and then be used again as normal.


## Common issues mitigated
### Null pointer dereference
- No concept of "pointers", as they are abstracted away into owned objects and "borrows".
- Ownership tracking ensures that uninitialized or moved objects aren't used.
- Borrows are always valid because they are only taken at function call sites => the owned object always outlives them.
- Inadvertently prevents pointer arithmetic from being possible either (dangerous operation).

### Use after free, double free & dangling borrows
- A "freed object" is moved into something else => memory enforcer recognizes this as a non-owned object.
- Cannot use a non-owned object -> ownership tracking ensures that objects are not used after they are "freed".
- References are only taken at call-sites, so it isn't possible for the underlying object to be freed while a borrow is active.

### Out-of-bounds access & buffer underflow / overflow
- All low-level memory access is bound checked in the `std::MemSeq[T]` class (from `std::Alloc[T]`)
- Bound checks can result in errors -> return `std::Ret[T, std::MemErr]` type

### Memory leaks
- Objects are uniquely owned => falls out of scope, then it is automatically freed after destructor is called.
- Memory leaks cannot occur, as the object will automatically be de-allocated upon destruction.
- Impossible to create a memory leak with borrows, as they are borrowed and never invalidated.
- Once the borrow falls out of scope, it is automatically freed, and the object is still valid.
- When the object falls out of scope, there will never be any active borrows to it.

### Use uninitialized variables
- Variables that are uninitialized are not usable -- memory enforcer, models uninitialized variables as "non-owned".
- Until variables are assigned some value, they cannot be used.
- At construction, all class attributes must be initialized (from the struct initialization).

### Type casting
- Requires a method on the target class to cast to the target type.
- For example, `let a = "123".to_int();` is the `std::Num` cast method.
- Some common casts are in classes to super-impose, ie `std::ToString`, which contains the `.to_string()` method.

### Data races
- Only 1 mutable borrow can ever be active per owned object at 1 time.
- Temporary ownership can be transferred with a mutable borrow.
- Any number of const borrows can be taken, because read-only objects/borrows are thread-safe.
- Cannot take a mutable and immutable borrow to the same object at the same time.
- Once the mutable borrow falls out of scope, another mutable borrow can be taken.

### Deadlocks
TODO: explain how this is mitigated
