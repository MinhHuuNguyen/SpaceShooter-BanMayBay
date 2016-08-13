package Controllers;

import Controllers.Bomb.BigBombSubscriber;
import Controllers.Bomb.SmallBombSubscriber;
import Controllers.Lock.LockSubscriber;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Minh on 8/12/2016.
 */
public class NotificationCenter {
     private Vector<BigBombSubscriber> bigBombSubscribers;
     private Vector<SmallBombSubscriber> smallBombSubscribers;
     private Vector<LockSubscriber> lockSubscribers;

     public NotificationCenter(){
         bigBombSubscribers = new Vector<BigBombSubscriber>();
         smallBombSubscribers = new Vector<SmallBombSubscriber>();
         lockSubscribers = new Vector<LockSubscriber>();

     }
     public void subscribeBigBomb(BigBombSubscriber bigBombSubscriber){
         bigBombSubscribers.add(bigBombSubscriber);
     }
     public void subscribeSmallBomb(SmallBombSubscriber smallBombSubscriber){
         smallBombSubscribers.add(smallBombSubscriber);
     }
     public void subscribeLock (LockSubscriber lockSubscriber){
         lockSubscribers.add(lockSubscriber);
     }
     public void onBigBombExplode(int x, int y){
         Iterator<BigBombSubscriber> bigBombSubscriberIterator = bigBombSubscribers.iterator();
         while (bigBombSubscriberIterator.hasNext()){
             BigBombSubscriber bigBombSubscriber = bigBombSubscriberIterator.next();
             if (!bigBombSubscriber.getGamObject().isAlive()){
                 bigBombSubscriberIterator.remove();
             }else {
                 bigBombSubscriber.onBigBombExplode(x, y);
             }
         }
     }
    public void onSmallBombExplode(int x, int y){
        Iterator<SmallBombSubscriber> smallBombSubscriberIterator = smallBombSubscribers.iterator();
        while (smallBombSubscriberIterator.hasNext()){
            SmallBombSubscriber smallBombSubscriber = smallBombSubscriberIterator.next();
            if (!smallBombSubscriber.getGamObject().isAlive()){
                smallBombSubscriberIterator.remove();
            }else {
                smallBombSubscriber.onSmallBombExplode(x, y);
            }
        }
    }
    public void onFreeze(int x, int y){
        Iterator<LockSubscriber> lockSubscriberIterator = lockSubscribers.iterator();
        while (lockSubscriberIterator.hasNext()){
            LockSubscriber lockSubscriber = lockSubscriberIterator.next();
            if (!lockSubscriber.getGamObject().isAlive()){
                lockSubscriberIterator.remove();
            } else {
                lockSubscriber.onFreeze(x, y);
            }
        }
    }
     public static final NotificationCenter instance = new NotificationCenter();
}
