package bg.projectoria.jmssh.commands

import bg.projectoria.jmssh.Environment
import org.codehaus.groovy.tools.shell.Shell

class LsBuiltin extends Builtin {

    LsBuiltin(Shell shell, Environment env) {
        super(shell, env, "ls", "dir")
    }

    List<MapEntry> call(Object... args) {
        if (args.length == 0)
            return [new Entry(env.getcwd(), env.resolve(env.getcwd()))]

        return args.collect() {
            name -> new Entry(name, env.resolve(name))
        }
    }

    class Entry extends MapEntry {
        Entry(String key, Object value) {
            super(key, value)
        }

        String toString() {
            "${this.getKey()}:\n${String.valueOf(this.getValue())}\n"
        }
    }
}
