# Negentropy Kotlin Multiplatform

Kotlin multiplatform implementation of Negentropy Range-Based-Set-Reconciliation protocol.

It's basically a binary search set-reconciliation algorithm.
You can read about the details [here](https://logperiodic.com/rbsr.html).
This code is basically a re-implementation of [Doug Hoyte's repository here](https://github.com/hoytech/negentropy)

## Storage

First, you need to create a storage instance. Currently only `Vector` is implemented. 
Add all the items in your collection with `insert(timestamp, hash)` and call `seal()`

    StorageVector().apply {
        insert(1678011277, "eb6b05c2e3b008592ac666594d78ed83e7b9ab30f825b9b08878128f7500008c".hexToByteArray())
        insert(1678011278, "39b916432333e069a4386917609215cc688eb99f06fed01aadc29b1b4b92d6f0".hexToByteArray())
        insert(1678011279, "abc81d58ebe3b9a87100d47f58bf15e9b1cbf62d38623f11d0f0d17179f5f3ba".hexToByteArray())

        seal()
    }

*  `timestamp` should be a unix timestamp
*  `id` should be a byte array of the event id

## Reconciliation

Create a Negentropy object:

    val ne = Negentropy(storage, 50_000)

* The second parameter (`50_000` above) is the `frameSizeLimit`. This can be omitted (or `0`) to permit unlimited-sized frames.

On the client-side, create an initial message, and then transmit it to the server, receive the response, and `reconcile` until complete (signified by returning `null` for `newMsg`):

    val msg = ne.initiate();

    while (msg !== null) {
        val response = <queryServer>(msg);
        val (newMsg, have, need) = ne.reconcile(msg);
        msg = newMsg;
        // handle have/need (there may be duplicates from previous calls to reconcile())
    }

*  The output `msg`s and the IDs in the `have`/`need` arrays are hex strings.

The server-side is similar, except it doesn't create an initial message, there are no `have`/`need` arrays, and `newMsg` will never be `null`:

    while (1) {
        val msg = <receiveMsgFromClient>();
        val reconciled = ne.reconcile(msg);
        respondToClient(reconciled.msg);
    }

* The `initiate()` and `reconcile()` methods are not suspending functions but they will take a while to process.

## Developer Setup

Make sure to have the following pre-requisites installed:
1. Java 17+
2. Android Studio or IntelliJ Idea CE

## Building

Build the app:
```bash
./gradlew clean assemble
```

## Testing
```bash
./gradlew test
```

## Running Conformance Tests with other implementations

Clone [Doug Hoyte's repository here](https://github.com/hoytech/negentropy) and clone this repository inside of it. 

Run `./gradlew assemble` to generate the `.jar` for the library and

```bash
perl test.pl kotlin,js
```

to run the test with a kotlin node and a javascript node

## Contributing

Issues can be logged on: [https://gitworkshop.dev/repo/negentropy-kmp](https://gitworkshop.dev/repo/negentropy-kmp)

[GitHub issues](https://github.com/vitorpamplona/negentropy-kmp/issues) and [pull requests](https://github.com/vitorpamplona/negentropy-kmp/pulls) here are also welcome. Translations can be provided via [Crowdin](https://crowdin.com/project/amethyst-social)

By contributing to this repository, you agree to license your work under the MIT license. Any work contributed where you are not the original author must contain its license header with the original author(s) and source.

# Contributors

<a align="center" href="https://github.com/vitorpamplona/negentropy-kmp/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=vitorpamplona/negentropy-kmp" />
</a>

# MIT License

<pre>
Copyright (c) 2024 Vitor Pamplona

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
</pre>
