package bg.projectoria.jmssh

import java.nio.file.FileAlreadyExistsException

class Environment {

    private final Directory root
    private Directory cwd
    private String cwdStr

    Environment() {
        root = new Directory()
        cwd = root
        cwdStr = "/"
    }

    String getcwd() {
        return cwdStr
    }

    void chdir(String dir) {
        // FIXME
    }

    void mkdir(String dir) {
        Directory parent = resolve(dirname(dir))
        def name = basename(dir)
        if (parent.containsKey(name))
            throw new FileAlreadyExistsException(dir)

        Directory child = new Directory()
        child.put("..", parent)
        parent.put(name, child)
    }

    Object resolve(String path) {
        path = path.trim()
        if (path.length() == 0)
            resolveError(path)

        List<String> components = Arrays.asList(path.split("/"))

        // Handle the case when standalone "/" is the whole path
        if (components.isEmpty())
            return root

        // Handle "/" at the beginning of the path (absolute path)
        if (components[0].length() == 0)
            return _resolve(root, components.subList(1, components.size()), path)

        // Every other case
        return _resolve(cwd, components, path)
    }

    private static Object _resolve(Object context, List<String> components, String fullPath) {
        while (!components.isEmpty()) {
            if (!(context instanceof Directory))
                resolveError(fullPath)

            context = ((Directory) context)[components.get(0)]
            if (context == null)
                resolveError(fullPath)

            components = components.subList(1, components.size())
        }
        return context
    }

    private static resolveError(String path) {
        throw new FileNotFoundException("No such context: $path")
    }


    static String basename(String name) {
        splitname(name).last()
    }

    static String dirname(String name) {
        splitname(name).first()
    }

    private static List<String> splitname(String name) {
        def sep = name.lastIndexOf("/")
        if (sep == -1)
            // No separator at all ("ala")
            return [".", name]
        else if (sep == 0)
            // Separator only at the beginning ("/ala")
            return ["/", name[1..-1]]
        else
            // Separator somewhere in the middle ("/ala/bala" or "ala/bala")
            return [name[0..(sep - 1)], name[(sep + 1)..-1]]
    }
}

class Directory extends TreeMap<String, Object> {

    Directory() {
        this.put(".", this)
    }

    @Override
    String toString() {
        this.collect() {name, obj -> "$name"}.join("\n")
    }
}
