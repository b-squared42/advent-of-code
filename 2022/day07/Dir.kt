package day07

class Dir {
    lateinit var parent: Dir
    var isRoot: Boolean
    var name: String
    var files: ArrayList<File>
    var dirs: ArrayList<Dir>

    constructor(parent: Dir?, name: String) {
        isRoot = name == "/"
        this.name = name
        this.files = ArrayList()
        this.dirs = ArrayList()
        if (parent != null) {
            this.parent = parent
        }
    }

    fun addDir(dir: Dir) {
        dirs.add(dir)
    }

    fun addFile(file: File) {
        files.add(file)
    }

    fun getSize():Int {
        var size = 0
        for (dir in dirs) {
            size += dir.getSize()
        }
        for (file in files) {
            size += file.size
        }
        return size
    }

    fun getMaxSizedDirs(max: Int): ArrayList<Int> {
        var list = ArrayList<Int>()
        for (dir in dirs) {
            var ds = dir.getSize()
            if (ds < max) list.add(ds)
            list.addAll(dir.getMaxSizedDirs(max))
        }
        return list

    }
}
