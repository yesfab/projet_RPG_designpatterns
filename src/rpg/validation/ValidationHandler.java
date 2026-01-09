package rpg.validation;

import rpg.core.Character;

public abstract class ValidationHandler {
    protected ValidationHandler nextHandler;

    public void setNext(ValidationHandler handler) {
        this.nextHandler = handler;
    }

    public abstract boolean validate(Character character);

    protected boolean passToNext(Character character) {
        if (nextHandler != null) {
            return nextHandler.validate(character);
        }
        return true;
    }
}