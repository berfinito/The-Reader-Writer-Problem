class ReadWriteLock {
    private Semaphore readS = new Semaphore(1);
    private Semaphore writeS = new Semaphore(1);

    /*Number of connections done */
    static private int readCount = 0;

    public void readLock() {

        try {
            readS.acquire();
            if (readCount == 0) {
                writeS.acquire();
            }
            readCount++;
            readS.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Read Lock! T-name: " + Thread.currentThread().getName());

    }

    public void writeLock() {

        try {

            writeS.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Write Lock! T-name: " + Thread.currentThread().getName());

    }

    public void readUnLock() {

        try {
            readS.acquire();
            readCount--;
            if (readCount == 0) {
                writeS.release();
            }
            readS.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Read UnLock! T-name: " + Thread.currentThread().getName());

    }

    public void writeUnLock() {

        writeS.release();
        System.out.println("Write UnLock! T-name: " + Thread.currentThread().getName());

    }
}