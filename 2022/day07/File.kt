package day07

class File {
    var parent: Dir
    var name: String
    var size: Int

    constructor(parent: Dir, name: String, size: Int) {
        this.parent = parent
        this.name = name
        this.size = size
    }
}
