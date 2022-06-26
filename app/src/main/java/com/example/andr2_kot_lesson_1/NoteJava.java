package com.example.andr2_kot_lesson_1;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class NoteJava {
    private final String title;
    private final String note;
    private final int color;

    public final String getTitle() {
        return this.title;
    }

    public final String getNote() {
        return this.note;
    }

    public final int getColor() {
        return this.color;
    }

    public NoteJava(String title, String note, int color) {
        this.title = title;
        this.note = note;
        this.color = color;
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.note;
    }

    public final int component3() {
        return this.color;
    }

    public final NoteJava copy(String title, String note, int color) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(note, "note");
        return new NoteJava(title, note, color);
    }

    public static NoteJava copy$default(NoteJava var0, String var1, String var2, int var3, int var4, Object var5) {
        if ((var4 & 1) != 0) {
            var1 = var0.title;
        }

        if ((var4 & 2) != 0) {
            var2 = var0.note;
        }

        if ((var4 & 4) != 0) {
            var3 = var0.color;
        }

        return var0.copy(var1, var2, var3);
    }

    @NotNull
    public String toString() {
        return "NoteJava(title=" + this.title + ", note=" + this.note + ", color=" + this.color + ")";
    }

    public int hashCode() {
        String var10000 = this.title;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.note;
        return (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Integer.hashCode(this.color);
    }

    public boolean equals(Object var1) {
        if (this != var1) {
            if (var1 instanceof NoteJava) {
                NoteJava var2 = (NoteJava) var1;
                if (Intrinsics.areEqual(this.title, var2.title) && Intrinsics.areEqual(this.note, var2.note) && this.color == var2.color) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
