package org.keithkim.typeql;

import org.junit.jupiter.api.Test;
import org.keithkim.typeql.UnionQuery.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnionQueryTest {
    @Test
    void empty() {
        UnionQuery unionQuery = new UnionQuery();
        assertEquals("", unionQuery.sql());
    }

    @Test
    void single() {
        UnionQuery unionQuery = new UnionQuery(new SelectQuery("SELECT 1"));
        assertEquals("SELECT 1", unionQuery.sql());
    }

    @Test
    void unionDefault2() {
        UnionQuery unionQuery = new UnionQuery(new SelectQuery("SELECT 1"),
                new SelectQuery("SELECT 2"));
        assertEquals("SELECT 1 UNION SELECT 2", unionQuery.sql());
    }

    @Test
    void union2() {
        UnionQuery unionQuery = new UnionQuery(Type.UNION,
                new SelectQuery("SELECT 1"),
                new SelectQuery("SELECT 2"));
        assertEquals("SELECT 1 UNION SELECT 2", unionQuery.sql());
    }

    @Test
    void unionAll2() {
        UnionQuery unionQuery = new UnionQuery(Type.UNION_ALL,
                new SelectQuery("SELECT 1"),
                new SelectQuery("SELECT 2"));
        assertEquals("SELECT 1 UNION ALL SELECT 2", unionQuery.sql());
    }

    @Test
    void unionDistinct2() {
        UnionQuery unionQuery = new UnionQuery(Type.UNION_DISTINCT,
                new SelectQuery("SELECT 1"),
                new SelectQuery("SELECT 2"));
        assertEquals("SELECT 1 UNION DISTINCT SELECT 2", unionQuery.sql());
    }

    @Test
    void union3() {
        UnionQuery unionQuery = new UnionQuery(new SelectQuery("SELECT 1"),
                new SelectQuery("SELECT 2"), new SelectQuery("SELECT 3"));
        assertEquals("SELECT 1 UNION SELECT 2 UNION SELECT 3", unionQuery.sql());
    }
}
