package com.example.calculator;

public enum Operation {
    BRACKET_LEFT("(", 0) {
        public double action(double x, double y) {
            return 0.0;
        }
    },
    BRACKET_RIGHT(")", 0) {
        public double action(double x, double y) {
            return 0.0;
        }
    },
    DIVISION("/", 2) {
        public double action(double x, double y) {
            return x / y;
        }
    },
    EXPONENTIAL("^", 3) {
        public double action(double x, double y) {
            return Math.pow(x, y);
        }
    },
    MINUS("-", 1) {
        public double action(double x, double y) {
            return x - y;
        }
    },
    MULTIPLICATION("*", 2) {
        public double action(double x, double y) {
            return x * y;
        }
    },
    PLUS("+", 1) {
        public double action(double x, double y) {
            return x + y;
        }
    };
    private final String title;
    private final int priority;

    Operation(String title, int priority) {
        this.title = title;
        this.priority = priority;

    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public static Operation getOperation(String operation){
        for (Operation constOperation: Operation.values()) {
            if(constOperation.getTitle().equals(operation))
                return constOperation;
        }
        return null;
    }

    public abstract double action(double x, double y);
}
