# Access modifiers
## Defaults
- By default, everything is public
- If one fragment is decorated as public, everything else becomes private
- If one fragment is decorated as protected or private, nothing else is affected

### Definitions
#### For classes
- `@meta::public`: exported out the module, any module can import it
- `@meta::protected`: invalid
- `@meta::private`: not exported out the module, only the module it is defined in can use it

#### Free function
- `@meta::public`: exported out the module, any module can import it
- `@meta::protected`: invalid
- `@meta::private`: not exported out the module, only the module it is defined in can use it

#### Class method
- `@meta::public`: exported out the class, any class' methods and free functions can use it
- `@meta::protected`: only the class it is defined in can use it, and any sub-classes can use it
- `@meta::private`: only the class it is defined in can use it

### Decorator parameters:
- `@meta::public`:
  - `path`: the root of modules that this is public to -- by default, it is public to all modules.
- `@meta::protected`:
  - `enforce`: whether to enforce the protected access modifier (debugging) -- compiler warnings will be generated for accessing protected members
  - `friends`: a list of friends that can access protected the member
- `@meta::private`:
  - `enforce`: whether to enforce the private access modifier (debugging) -- compiler warnings will be generated for accessing private members
  - `friends`: a list of friends that can access private the member
