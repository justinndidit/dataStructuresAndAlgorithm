# Data Structures & Algorithms

A personal, from-scratch implementation of classic data structures and a growing set of
LeetCode solutions, written in Java 21 and built with Gradle.

Everything here is hand-rolled — the collections are built from their primitives (nodes,
arrays) rather than wrapping `java.util`, so the internals stay visible.

## Requirements

- **JDK 21** — the Gradle toolchain will resolve/download one automatically via the
  [foojay resolver](https://github.com/gradle/foojay-toolchains) if it isn't installed.
- No local Gradle install needed; use the bundled wrapper (Gradle 9.2.0).

## Build & run

```bash
./gradlew build        # compile + test
./gradlew jar          # produce lib/build/libs/lib.jar
./gradlew clean
```

`Main` is a scratch driver used to exercise the structures by hand. The project applies the
`java-library` plugin, so there is **no `./gradlew run` task** — run the class off the
compiled output:

```bash
./gradlew classes
java -cp lib/build/classes/java/main com.justinndidit.Main
```

```
DSA!!!!
linked list is empty
[Favour]->[Anuoluwapo]
[Surgee]->[Olasoji]->[Favour]->[Anuoluwapo]
[Olasoji]->[Favour]->[Anuoluwapo]
false
3
Olasoji
false
```

## Layout

```
dsa/
├── settings.gradle              # root project 'dsa', includes :lib
├── gradle/libs.versions.toml    # version catalog
└── lib/
    ├── build.gradle             # java-library, Java 21 toolchain, JUnit 4
    └── src/main/java/com/justinndidit/
        ├── Main.java                            # scratch driver
        ├── linkedList/
        │   ├── SinglyLinkedList.java            # generic singly linked list
        │   └── DoublyLinkedList.java            # stub — node type only
        ├── hashTable/
        │   └── HashTableWithLinkedList.java     # separate-chaining hash table
        └── leetcode/
            ├── TwoSum.java                      # LC 1
            └── GroupAnagram.java                # LC 49
```

## What's implemented

### `SinglyLinkedList<T>`

Head/tail tracked, size cached. Rejects `null` elements with `IllegalArgumentException`.

| Method | Description | Time |
| --- | --- | --- |
| `add(T)` | append at tail | O(1) |
| `addFirst(T)` | prepend at head | O(1) |
| `insert(int, T)` | insert at index | O(n) |
| `remove(T)` | remove first match | O(n) |
| `get(int)` | element at index | O(n) |
| `contains(T)` | membership test | O(n) |
| `size()` / `isEmpty()` | cached count | O(1) |
| `toString()` | renders as `[a]->[b]->[c]` | O(n) |

### `HashTableWithLinkedList<K, V>`

Separate chaining: a fixed array of 16 buckets, each bucket a `SinglyLinkedList` of
key/value `Entry` objects. Index is `Math.abs(key.hashCode()) % 16`. `null` keys are
rejected.

| Method | Description | Time (avg / worst) |
| --- | --- | --- |
| `put(K, V)` | insert or overwrite existing key | O(1) / O(n) |
| `get(K)` | value for key, or `null` | O(1) / O(n) |
| `remove(K)` | remove by key | O(1) / O(n) |
| `containsKey(K)` | membership test | O(1) / O(n) |
| `size()` | entry count | O(1) |

The chain walk uses `get(i)` on the bucket list, which is itself O(i) — so a bucket of
length *k* costs O(k²) to scan rather than O(k). Fine at this scale, worth revisiting when
the list gains an iterator.

### LeetCode

| # | Problem | Class | Approach |
| --- | --- | --- | --- |
| 1 | Two Sum | `TwoSum.twoSum` | complement lookup in a `HashMap`, one pass |
| 49 | Group Anagrams | `GroupAnagram.GroupAnagramSort` | key = sorted characters — O(n·k log k) |
| 49 | Group Anagrams | `GroupAnagram.GroupAnagramCount` | key = 26-slot character-count vector — O(n·k) |

## Status

Work in progress. Known gaps, in rough priority order:

- **`TwoSum.twoSum` loops to a hard-coded `i < 10`** instead of `nums.length`. It throws
  `ArrayIndexOutOfBoundsException` on arrays shorter than 10 with no earlier match, and
  silently ignores elements past index 9 on longer ones.
- **`SinglyLinkedList.insert` is off by one** for `index > 0` — it links the new node
  *after* the node at `index`. Its bound check (`index >= size`) also rules out appending
  at the end, which `insert` should normally allow.
- **`HashTableWithLinkedList.remove` never decrements `size`**, so the count drifts high
  after any removal.
- **No load-factor tracking or resize** on the hash table; the bucket array stays at 16, so
  chains grow without bound.
- **`HashTableWithLinkedList.toString`** returns an empty string.
- **`DoublyLinkedList`** is a stub — the `Node` type exists, no operations yet.
- **No tests.** JUnit 4.13.2 is wired up in `lib/build.gradle` but `src/test` doesn't exist
  yet; the structures are currently exercised only by hand through `Main`.

## Roadmap

- Unit tests covering each structure's edge cases (empty, single element, head/tail
  removal, hash collisions)
- Finish `DoublyLinkedList`
- Dynamic array, stack, queue, binary search tree, heap, graph
- Sorting and searching algorithms
- More LeetCode solutions, grouped by pattern

## License

No license declared yet.
