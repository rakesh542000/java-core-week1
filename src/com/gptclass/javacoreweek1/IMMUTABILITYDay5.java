package com.gptclass.javacoreweek1;

import java.util.*;

/**
 * DAY 5: Immutability + equals() & hashCode()
 *
 * This file demonstrates:
 * 1. Why HashSet allows duplicates without proper equals/hashCode
 * 2. Why mutating a HashMap key breaks retrieval
 * 3. How immutability prevents these bugs entirely
 */

/* -------------------- IMMUTABLE VALUE OBJECT -------------------- */

/**
 * ImmutableUser is a safe value object.
 *
 * - final class → cannot be subclassed
 * - final fields → state cannot change
 * - no setters → immutability enforced
 *
 * This makes it SAFE to use as a HashMap key.
 */
final class ImmutableUser {

    private final int id;
    private final String name;

    public ImmutableUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    /**
     * Equality is based ONLY on id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ImmutableUser other = (ImmutableUser) obj;
        return this.id == other.id;
    }

    /**
     * hashCode uses the SAME field as equals().
     * This satisfies the equals/hashCode contract.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

/* -------------------- MUTABLE OBJECT (INTENTIONALLY DANGEROUS) -------------------- */

/**
 * MutableUser is intentionally mutable to demonstrate bugs.
 *
 * DO NOT use mutable objects as HashMap keys in real systems.
 */
class MutableUser {

    int id;
    String name;

    public MutableUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Equality based on id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MutableUser other = (MutableUser) obj;
        return this.id == other.id;
    }

    /**
     * hashCode must match equals().
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(int id) {
        this.id = id; // Dangerous when used as Map key
    }
}

/* -------------------- DEMONSTRATION -------------------- */

public class IMMUTABILITYDay5 {

    public static void main(String[] args) {

        /* ---------- HashSet behavior ---------- */

        Set<MutableUser> users = new HashSet<>();
        users.add(new MutableUser(1, "Rakesh"));
        users.add(new MutableUser(1, "Rakesh"));

        // Correct output: 1 (after fixing equals + hashCode)
        System.out.println("HashSet size (should be 1): " + users.size());

        /* ---------- HashMap BREAKS with mutable key ---------- */

        MutableUser mutableKey = new MutableUser(10, "Admin");
        Map<MutableUser, String> roleMap = new HashMap<>();

        roleMap.put(mutableKey, "ADMIN");

        // Mutating key AFTER insertion breaks HashMap
        mutableKey.setId(99);

        // Returns null because hashCode changed → bucket mismatch
        System.out.println("Mutable key lookup (should be null): " + roleMap.get(mutableKey));

        /* ---------- HashMap WORKS with immutable key ---------- */

        ImmutableUser immutableKey = new ImmutableUser(20, "Rakesh");
        Map<ImmutableUser, String> safeMap = new HashMap<>();

        safeMap.put(immutableKey, "ADMIN");

        // Safe: state cannot change
        System.out.println("Immutable key lookup (should be ADMIN): " + safeMap.get(immutableKey));

        // immutableKey.id = 30; ❌ Compile-time error (immutability enforced)
    }
}
