### GraphQL Query

```graphql
{
    sayHello
}
```

```graphql
{
    sayHelloTo(name: "Sashank", age: 31)
}
```

```graphql
{
    random
}
```

#### Combining all the interfaces together

```graphql
{
    sayHello
    random
    sayHelloTo(name: "Sashank", age: 31)
}
```
