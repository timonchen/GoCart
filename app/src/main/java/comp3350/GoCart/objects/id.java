package comp3350.GoCart.objects;

import java.util.Objects;

public class id{
    private String id;

    public id(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        id id1 = (id) o;
        return Objects.equals(id, id1.id);
    }

    @Override
    public String toString() {
        return "id{" +
                "id='" + id + '\'' +
                '}';
    }
}
