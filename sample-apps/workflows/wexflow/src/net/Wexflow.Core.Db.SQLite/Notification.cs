﻿namespace Wexflow.Core.Db.SQLite
{
    public class Notification : Core.Db.Notification
    {
        public const string COLUMN_NAME_ID = "ID";
        public const string COLUMN_NAME_ASSIGNED_BY = "ASSIGNED_BY";
        public const string COLUMN_NAME_ASSIGNED_ON = "ASSIGNED_ON";
        public const string COLUMN_NAME_ASSIGNED_TO = "ASSIGNED_TO";
        public const string COLUMN_NAME_MESSAGE = "MESSAGE";
        public const string COLUMN_NAME_IS_READ = "IS_READ";

        public const string TABLE_STRUCT = "(" + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
                                                        + COLUMN_NAME_ASSIGNED_BY + " INTEGER, "
                                                        + COLUMN_NAME_ASSIGNED_ON + " TEXT, "
                                                        + COLUMN_NAME_ASSIGNED_TO + " INTEGER, "
                                                        + COLUMN_NAME_MESSAGE + " TEXT, "
                                                        + COLUMN_NAME_IS_READ + " INTEGER)";

        public long Id { get; set; }

        public override string GetDbId()
        {
            return Id.ToString();
        }
    }
}
