# Iteration, Indexing & Slicing
## Iteration
- 2nd class references mean that references cannot be returned, posing a difficulty for iteration.
- Iteration is possible by passing a closure or function to the `iter` method.
- The `for` loop is syntactic sugar for iteration
  - Iterating variables are the parameters.
  - The body of the loop is the function.
- Example:
```s++
for x, y, z in some_collection.iter() {
    // do something with x, y, z
}
```
- Is actually:
```s++
some_collection.iter((x, y, z) => {
    // do something with x, y, z
});
```


- The `iter` method will then call the function with each element of the collection.
- Automatically applies the correct reference to the parameters.

---

#### Temp links for iteration with 2nd-class references
- https://borretti.me/article/second-class-references
- https://lobste.rs/s/sizjds/
