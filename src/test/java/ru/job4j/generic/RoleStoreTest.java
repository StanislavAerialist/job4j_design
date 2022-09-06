package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsEngineer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Engineer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsBaker() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        store.add(new Role("1", "Engineer"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Baker");
    }

    @Test
    void whenReplaceThenRolenameIsBaker() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.replace("1", new Role("1", "Baker"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Baker");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Engineer"));
        store.replace("10", new Role("10", "Baker"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Engineer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsBaker() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Baker");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        boolean rsl = store.replace("1", new Role("1", "Engineer"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        boolean rsl = store.replace("10", new Role("1", "Engineer"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteSuccessThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotSuccessThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        boolean rsl = store.delete("10");
        assertThat(rsl).isFalse();
    }
}