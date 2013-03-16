package bg.projectoria.jmssh

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
