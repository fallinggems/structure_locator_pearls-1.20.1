package net.gabriel.gabruhaddons.util;

import com.mojang.realmsclient.gui.task.DataFetcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DelayedActivation {
    private static final List<Task> TASKS = new ArrayList<>();

    public static void schedule(int ticks, Runnable action) {
        TASKS.add(new Task(ticks, action));
    }

    public static void tick() {

        Iterator<Task> it = TASKS.iterator();

        while (it.hasNext()) {

            Task task = it.next();

            task.ticks--;

            if (task.ticks <= 0) {
                task.action.run();
                it.remove();
            }
        }
    }

    private static class Task {

        int ticks;
        Runnable action;

        Task(int ticks, Runnable action) {
            this.ticks = ticks;
            this.action = action;
        }
    }

}
