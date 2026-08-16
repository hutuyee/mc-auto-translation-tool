package org.universaltranslator.core;

/** Shared responsive geometry for the platform settings screens. */
public final class SettingsScreenLayout {
    public static final int BUTTON_HEIGHT = 20;

    private SettingsScreenLayout() {
    }

    public static Geometry calculate(int screenWidth, int screenHeight) {
        int safeWidth = Math.max(40, screenWidth);
        int availableWidth = Math.max(40, safeWidth - 8);
        int totalWidth = safeWidth >= 200
                ? Math.max(180, Math.min(310, safeWidth - 20))
                : Math.min(310, availableWidth);
        int gap = totalWidth >= 120 ? 8 : 4;
        int buttonWidth = Math.max(16, (totalWidth - gap) / 2);
        int left = Math.max(0, (safeWidth - totalWidth) / 2);

        int top = Math.max(20, Math.min(44, 20 + Math.max(0, screenHeight - 220) / 4));
        int rowStep = screenHeight >= 300 ? 26 : (screenHeight >= 260 ? 22 : 20);
        int targetY = top + rowStep * 7 + 2;
        int endpointY = targetY + (screenHeight >= 300 ? 32 : 28);
        int saveY = screenHeight >= 330
                ? 296 : Math.max(endpointY + 22, screenHeight - 24);

        // Minecraft normally exposes at least 240 logical pixels, but mobile launchers,
        // custom GUI scales and embedded windows can be smaller. Keep the action row on-screen
        // by shifting the form upward instead of leaving Save and Cancel outside mouse reach.
        int bottomLimit = Math.max(BUTTON_HEIGHT, screenHeight - 4);
        int overflow = saveY + BUTTON_HEIGHT - bottomLimit;
        if (overflow > 0) {
            top -= overflow;
            targetY = top + rowStep * 7 + 2;
            endpointY = targetY + (screenHeight >= 300 ? 32 : 28);
            saveY = screenHeight >= 330
                    ? 296 : Math.max(endpointY + 22, screenHeight - 24);
        }

        return new Geometry(left, left + buttonWidth + gap, totalWidth, buttonWidth,
                top, rowStep, targetY, endpointY, saveY);
    }

    public static final class Geometry {
        private final int left;
        private final int right;
        private final int totalWidth;
        private final int buttonWidth;
        private final int top;
        private final int rowStep;
        private final int targetY;
        private final int endpointY;
        private final int saveY;

        private Geometry(int left, int right, int totalWidth, int buttonWidth,
                         int top, int rowStep, int targetY, int endpointY, int saveY) {
            this.left = left;
            this.right = right;
            this.totalWidth = totalWidth;
            this.buttonWidth = buttonWidth;
            this.top = top;
            this.rowStep = rowStep;
            this.targetY = targetY;
            this.endpointY = endpointY;
            this.saveY = saveY;
        }

        public int left() { return left; }
        public int right() { return right; }
        public int totalWidth() { return totalWidth; }
        public int buttonWidth() { return buttonWidth; }
        public int top() { return top; }
        public int rowStep() { return rowStep; }
        public int targetY() { return targetY; }
        public int endpointY() { return endpointY; }
        public int saveY() { return saveY; }

        public int row(int index) {
            return top + rowStep * index;
        }
    }
}
