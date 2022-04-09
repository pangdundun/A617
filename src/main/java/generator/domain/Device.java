package generator.domain;

import java.io.Serializable;

/**
 * @TableName device
 */
public class Device implements Serializable {
    /**
     *
     */
    private Object id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String dateRegistered;

    /**
     *
     */
    private String dateVisited;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Object getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     *
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     */
    public String getDateRegistered() {
        return dateRegistered;
    }

    /**
     *
     */
    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    /**
     *
     */
    public String getDateVisited() {
        return dateVisited;
    }

    /**
     *
     */
    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Device other = (Device) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getDateRegistered() == null ? other.getDateRegistered() == null : this.getDateRegistered().equals(other.getDateRegistered()))
                && (this.getDateVisited() == null ? other.getDateVisited() == null : this.getDateVisited().equals(other.getDateVisited()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDateRegistered() == null) ? 0 : getDateRegistered().hashCode());
        result = prime * result + ((getDateVisited() == null) ? 0 : getDateVisited().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", dateRegistered=").append(dateRegistered);
        sb.append(", dateVisited=").append(dateVisited);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}