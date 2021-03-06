/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2014, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.jca.core.tracer;

/**
 * A trace event
 *
 * @author <a href="mailto:jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public class TraceEvent
{
   /** Get connection listener */
   public static final int GET_CONNECTION_LISTENER = 0;

   /** Get connection listener (New) */
   public static final int GET_CONNECTION_LISTENER_NEW = 1;

   /** Get interleaving connection listener */
   public static final int GET_INTERLEAVING_CONNECTION_LISTENER = 2;

   /** Get connection listener interleaving (New) */
   public static final int GET_INTERLEAVING_CONNECTION_LISTENER_NEW = 3;

   /** Return connection listener */
   public static final int RETURN_CONNECTION_LISTENER = 10;

   /** Return connection listener with kill */
   public static final int RETURN_CONNECTION_LISTENER_WITH_KILL = 11;

   /** Return interleaving connection listener */
   public static final int RETURN_INTERLEAVING_CONNECTION_LISTENER = 12;

   /** Return interleaving connection listener with kill */
   public static final int RETURN_INTERLEAVING_CONNECTION_LISTENER_WITH_KILL = 13;

   /** Clear connection listener */
   public static final int CLEAR_CONNECTION_LISTENER = 14;

   /** Enlist connection listener */
   public static final int ENLIST_CONNECTION_LISTENER = 20;

   /** Enlist connection listener (Failed) */
   public static final int ENLIST_CONNECTION_LISTENER_FAILED = 21;

   /** Enlist interleaving connection listener */
   public static final int ENLIST_INTERLEAVING_CONNECTION_LISTENER = 22;

   /** Enlist interleaving connection listener (Failed) */
   public static final int ENLIST_INTERLEAVING_CONNECTION_LISTENER_FAILED = 23;

   /** Delist connection listener */
   public static final int DELIST_CONNECTION_LISTENER = 30;

   /** Delist connection listener (Failed) */
   public static final int DELIST_CONNECTION_LISTENER_FAILED = 31;

   /** Delist interleaving connection listener */
   public static final int DELIST_INTERLEAVING_CONNECTION_LISTENER = 32;

   /** Delist interleaving connection listener (Failed) */
   public static final int DELIST_INTERLEAVING_CONNECTION_LISTENER_FAILED = 33;

   /** Delist rollbacked connection listener */
   public static final int DELIST_ROLLEDBACK_CONNECTION_LISTENER = 34;

   /** Get connection */
   public static final int GET_CONNECTION = 40;

   /** Return connection */
   public static final int RETURN_CONNECTION = 41;

   /** Clear connection */
   public static final int CLEAR_CONNECTION = 42;

   /** Exception */
   public static final int EXCEPTION = 50;

   /** The pool */
   private String pool;

   /** The thread id */
   private long threadId;

   /** The type */
   private int type;

   /** The time stamp */
   private long timestamp;

   /** The connection listener */
   private String cl;

   /** The payload */
   private String payload;

   /**
    * Constructor
    * @param pool The pool
    * @param type The event type
    * @param cl The connection listener
    */
   TraceEvent(String pool, int type, String cl)
   {
      this(pool, Thread.currentThread().getId(), type, System.currentTimeMillis(), cl, "");
   }

   /**
    * Constructor
    * @param pool The pool
    * @param type The event type
    * @param cl The connection listener
    * @param payload The payload
    */
   TraceEvent(String pool, int type, String cl, String payload)
   {
      this(pool, Thread.currentThread().getId(), type, System.currentTimeMillis(), cl, payload);
   }

   /**
    * Parse constructor
    * @param pool The pool
    * @param threadId The thread id
    * @param type The event type
    * @param timestamp The timestamp
    * @param cl The connection listener
    * @param payload The payload
    */
   private TraceEvent(String pool, long threadId, int type, long timestamp, String cl, String payload)
   {
      this.pool = pool != null ? pool.replace('-', '_') : "Empty"; 
      this.threadId = threadId;
      this.type = type;
      this.timestamp = timestamp;
      this.cl = cl;
      this.payload = payload;
   }

   /**
    * Get the pool
    * @return The value
    */
   public String getPool()
   {
      return pool;
   }

   /**
    * Get the thread id
    * @return The value
    */
   public long getThreadId()
   {
      return threadId;
   }

   /**
    * Get the type
    * @return The value
    */
   public int getType()
   {
      return type;
   }

   /**
    * Get the timestamp
    * @return The value
    */
   public long getTimestamp()
   {
      return timestamp;
   }

   /**
    * Get the connection listener
    * @return The value
    */
   public String getConnectionListener()
   {
      return cl;
   }

   /**
    * Get the payload
    * @return The value
    */
   public String getPayload()
   {
      return payload;
   }

   /**
    * {@inheritDoc}
    */
   public String toString()
   {
      StringBuilder sb = new StringBuilder();

      sb.append("IJTRACER");
      sb.append("-");
      sb.append(pool);
      sb.append("-");
      sb.append(Long.toString(threadId));
      sb.append("-");
      sb.append(Integer.toString(type));
      sb.append("-");
      sb.append(Long.toString(timestamp));
      sb.append("-");
      sb.append(cl);
      sb.append("-");
      sb.append(payload);

      return sb.toString();
   }

   /**
    * As text
    * @param event The event
    * @return The text
    */
   public static String asText(TraceEvent event)
   {
      switch (event.getType())
      {
         case GET_CONNECTION_LISTENER:
            return "getConnectionListener()";
         case GET_CONNECTION_LISTENER_NEW:
            return "getConnectionListener(true)";
         case GET_INTERLEAVING_CONNECTION_LISTENER:
            return "getConnectionListener() (I)";
         case GET_INTERLEAVING_CONNECTION_LISTENER_NEW:
            return "getConnectionListener(true) (I)";
         case RETURN_CONNECTION_LISTENER:
            return "returnConnectionListener()";
         case RETURN_CONNECTION_LISTENER_WITH_KILL:
            return "returnConnectionListener(true)";
         case RETURN_INTERLEAVING_CONNECTION_LISTENER:
            return "returnConnectionListener() (I)";
         case RETURN_INTERLEAVING_CONNECTION_LISTENER_WITH_KILL:
            return "returnConnectionListener(true) (I)";
         case CLEAR_CONNECTION_LISTENER:
            return "clearConnectionListener()";
         case ENLIST_CONNECTION_LISTENER:
            return "enlistResource()";
         case ENLIST_CONNECTION_LISTENER_FAILED:
            return "enlistResource(false)";
         case ENLIST_INTERLEAVING_CONNECTION_LISTENER:
            return "enlistResource() (I)";
         case ENLIST_INTERLEAVING_CONNECTION_LISTENER_FAILED:
            return "enlistResource(false) (I)";
         case DELIST_CONNECTION_LISTENER:
            return "delistResource()";
         case DELIST_CONNECTION_LISTENER_FAILED:
            return "delistResource(false)";
         case DELIST_INTERLEAVING_CONNECTION_LISTENER:
            return "delistResource() (I)";
         case DELIST_INTERLEAVING_CONNECTION_LISTENER_FAILED:
            return "delistResource(false) (I)";
         case DELIST_ROLLEDBACK_CONNECTION_LISTENER:
            return "delistResource() (R)";
         case GET_CONNECTION:
            return "getConnection(" + event.getPayload() + ")";
         case RETURN_CONNECTION:
            return "returnConnection(" + event.getPayload() + ")";
         case CLEAR_CONNECTION:
            return "clearConnection(" + event.getPayload() + ")";
         case EXCEPTION:
            return "exception";
         default:
      }

      return "";
   }

   /**
    * Parse a trace event
    * @param data The data string
    * @return The event
    */
   public static TraceEvent parse(String data)
   {
      String[] raw = data.split("-");

      String header = raw[0];
      String p = raw[1];
      long tid = Long.parseLong(raw[2]);
      int t = Integer.parseInt(raw[3]);
      long ts = Long.parseLong(raw[4]);
      String c = raw[5];
      String pyl = "";

      if (raw.length == 7)
         pyl = raw[6];

      return new TraceEvent(p, tid, t, ts, c, pyl);
   }
}
