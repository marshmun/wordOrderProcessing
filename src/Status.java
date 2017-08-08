
public enum Status {
    INITIAL,
    ASSIGNED,
    IN_PROGRESS,
    DONE;

    public Status next() {
        switch ( this ) {
            case INITIAL:
                return ASSIGNED;
            case ASSIGNED:
                return IN_PROGRESS;
            case IN_PROGRESS:
                return DONE;
            default:
                return null;
        }
    }
}