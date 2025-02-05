# Spreetail Sample Command Line App: Multivalue String Dictionary

## Summary
The Multi-Value Dictionary app is a command line application that stores a multivalue dictionary in memory. All keys and members are strings.

## Main APIs:
1. `Keys`: Returns all the keys in the dictionary.  Order is not guaranteed.
2. `Members`: Returns the collection of strings for the given key.  Return order is not guaranteed.  Returns an error if the key does not exists.
3. `Add`: Adds a member to a collection for a given key. Displays an error if the member already exists for the key.
4. `Remove`: Removes a member from a key.  If the last member is removed from the key, the key is removed from the dictionary. If the key or member does not exist, displays an error.
5. `Removall`: Removes all members for a key and removes the key from the dictionary. Returns an error if the key does not exist.
6. `Clear`: Removes all keys and all members from the dictionary.
7. `Keyexists`: Returns whether a key exists or not.
8. `Memberexists`: Returns whether a member exists within a key.  Returns false if the key does not exist.
9. `Allmembers`: Returns all the members in the dictionary.  Returns nothing if there are none. Order is not guaranteed.
10. `Items`: Returns all keys in the dictionary and all of their members.  Returns nothing if there are none.  Order is not guaranteed.

## How to Run the App
1. download the file from github or clone the repository

```shell
wget -O MultiValueDictApp.java https://raw.githubusercontent.com/JinZgitH/spreetail/refs/heads/main/MultiValueDictApp.java
wget -O HELP.md https://raw.githubusercontent.com/JinZgitH/spreetail/refs/heads/main/HELP.md
```

2. Compile the `MultiValueDictApp.java`

```shell
javac MultiValueDictApp.java 
```

3. Run the program

```shell
java MultiValueDictApp
```