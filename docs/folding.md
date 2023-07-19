# Folding (NOT FULLY IMPLEMENTED)
- Special operation applicable to variadic types
- Allows to fold a binary operation over a parameter pack
- Allows to fold a method over a parameter pack

#### Folding equivalences (same as C++)
| Fold type         | Expression      | Equivalent expression                |
|-------------------|-----------------|--------------------------------------|
| Unary Right Fold  | `E op ...`      | `E1 op (... op (EN-1 op EN))`        |
| Unary Left Fold   | `... op E`      | `((E1 op E2) op ...) op EN`          |
| Binary Left Fold  | `E op ... op I` | `E1 op (... op (EN-1 op (EN op I)))` |
| Binary Right Fold | `I op ... op E` | `(((I op E1) op E2) op ... ) op EN`  |

#### Binary folding
```s++
fn function(...args: std::Num) -> std::Num {
    return 0 + ... + args;
}
```

#### Unary folding
```s++
fn function(...args: std::Num) -> std::Num {
    return ... && args;
}
```
