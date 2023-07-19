# Modules & importing
## Module definition
- The `mod` keyword defined the `.spp` file to be included in the compiler-tree.
- The name of the module, including its directory structure, must be provided, and will be unique.
- The module in the `src/com/ex/mods/m1.spp` folder will be tagged as `mod src::com::ex::mods::m1`
- Directories in the compile-tree must all start with an alphabetic character, and cannot contain spaces.
- Module names mirroring directory structure ensures uniqueness and no conflict.

### Visibility
- Public modules, ie `@meta::public`, are accessible to any other module in the program.
- Protected modules, ie `@meta::protected`, are accessible to only the current module -- same as Rust's `@meta::public(path="src")`.
- Private modules, ie `@meta::private`, are included in the compile-tree but aren't accessible to any other module.

### IDEs
- IDEs will fill in the `mod` tag as part of the file template.

## Importing
- Import statements are defined under the `mod` keyword.
- The `use` keyword is followed by the module path and then types / functions to import.
- Use `sup` to import from the super module (parent directory).
- Use `src` to refer to the root of the `src` folder.
```s++
mod src.com.example.module1;

# Import local structures.
use src::data_structures::my_struct;
use sup::utils::vector_tools -> vector_tools;

# Import the vector and optional class from the standard library.
use std::vector::vector;
use std::optional::{optional, ok, some};

# Import minecraft data structures from GitHub S++ project (cached locally).
use vcs::minecraft::models::block::{block, block_state};
use vcs::minecraft::particles::{particle, particle_type};
use vcs::minecraft::models::entity::{entity, entity_type};

# Import the lockers library from the lib folder.
use lib::lockers::lockers;
```
- The automatic namespacing of types follows the module path, **but NOT including the filename**.
  - `use std::vec::Vec;` will import the `Vec` class as `std::Vec`, **not** `std::vec::Vec`

### What can be imported
#### Single type -- one of:
- Import one type from a module by extending the `use` with another `::Type;`
- Import one type inside a group of imports: `::{Type};`

#### Multiple types:
- Import multiple types inside an import group: `::{Type1, Type2};`

#### All types:
- Import all types from a module: `::*;`

### Import locations
| Folder tag | Description                                                             |
|------------|-------------------------------------------------------------------------|
| `src`      | The `src` folder of the program, root of all 2nd party content          |
| `lib`      | The `lib` folder of the program, root of all 3rd party static content   |
| `std`      | The `std` folder of the S++ standard library (1st party code)           |
| `vcs`      | The `vcs` folder of the program, cache of all 3rd party dynamic content |
| `sup`      | The super module (parent directory)                                     |

<BR>

#### src
- Root folder of the program, root of all 2nd party content
- Only folder that can contain user-defined modules
- Enforced by the compiler - the `main.spp` will be in at the root of the `src` folder

#### lib
- Root folder of the program, root of all 3rd party static content
- Download static 3rd party content, where it is not hosted on a vcs
- Prefer to use vcs over lib, as it allows for automatic updates of the code

#### std
- Root folder of the S++ standard library (1st party code)
- Contains all the standard library modules
- Simple structure to optimize imports

#### vcs
- Root folder of the program, cache of all 3rd party dynamic content
- Allows importing directly from a vcs, such as GitHub or GitLab
- Allows for automatic updates of the code
- The `config.toml` file defines the URLs for each project to get from vcs

To use uncommon version control systems, the user can add a config file to the `vcs` folder. The config file must
follow the following specification (GitHub example), so that the compiler knows how to download the code from the
vcs -- it's a very simple system, and can be easily extended to support any vcs system:
```toml
[meta]
name = "github"
prefix = "gh"
url = "https://github.com/<user>/<repo>/blob/<branch>"

[commands]
clone = "git clone"
fetch = "git fetch"
pull  = "git pull"
```

Here is the GitLab example:
```toml
[meta]
name = "gitlab"
prefix = "gl"
url = "https://gitlab.com/<user>/<repo>/blob/<branch>"

[commands]
clone = "git clone"
fetch = "git fetch"
pull  = "git pull"
```

To register the project to import from vcs:
```toml
[vcs]
minecraft = "https://.../minecraft.git"
```
The correct VCS is used based on the URl regex comparison to known VCS URLs.
