#!/usr/bin/env python
# coding: utf-8

import argparse
import getpass
import pymysql
import io

if __name__ == "__main__":
    args = argparse.ArgumentParser(
        description="CSV to SQL importer for OnlyWeebs"
    )
    args.add_argument(
        nargs="+",
        type=str,
        dest="files",
        help="CSV files to parse",
        metavar="FILE",
    )
    args.add_argument(
        "-H",
        "--host",
        metavar="HOST",
        dest="host",
        type=str,
        nargs="?",
        help="Host database",
        default="localhost",
    )
    args.add_argument(
        "-p",
        "--port",
        metavar="PORT",
        dest="port",
        type=int,
        nargs="?",
        help="Listening port of the MySQL/MariaDB database on your device",
        default=3306,
    )
    args.add_argument(
        "-u",
        "--username",
        metavar="USERNAME",
        dest="username",
        type=str,
        nargs="?",
        help="Username to connect to database",
        required=True,
    )
    args.add_argument(
        "-P",
        "--password",
        metavar="PASSWORD",
        dest="password",
        type=str,
        nargs="?",
        help="Password of the account to use to connect to database",
        default=None,
    )
    args.add_argument(
        "-d",
        "--database",
        metavar="DATABASE",
        dest="database",
        type=str,
        nargs="?",
        help="Database to use",
        required=True,
    )
    args = args.parse_args()

    if args.password is None:
        args.password = getpass.getpass(f"{args.username}'s password: ")

    db = pymysql.connect(
        host=args.host,
        user=args.username,
        password=args.password,
        database=args.database,
        charset="utf8mb4",
    )
    cursor = db.cursor()

    for file in args.files:
        with io.open(file, "r", encoding="utf-8") as f:
            for line in f:
                line = (
                    line[:-1].split("¤")
                    if line[-1:] == "\n"
                    else line.split("¤")
                )
                if line[0] != "Title" and line[0] != "":
                    try:
                        cursor.execute(
                            'SELECT id FROM pegi WHERE pegi LIKE "{}";'.format(
                                line[8]
                            )
                        )
                        pegi_id = cursor.fetchone()
                        if pegi_id is None:
                            cursor.execute(
                                'INSERT INTO pegi(pegi) VALUES("{}");'.format(
                                    line[8]
                                )
                            )
                    except pymysql.err.IntegrityError as interr:
                        pass
                    cursor.execute(
                        'SELECT id from pegi where pegi LIKE "{}";'.format(
                            line[8]
                        )
                    )
                    pegi_id = cursor.fetchone()[0]
                    try:
                        cursor.execute("SELECT title FROM anime WHERE title LIKE \"{}\";".format(line[0].strip('\'"[] ')))
                        title = cursor.fetchone()
                        if title is None:
                            cursor.execute(
                                'INSERT INTO anime(title, title_english, synopsis, img_url, episodes, pegi_id, aired, airing) VALUES("{}", "{}", "{}", "{}", {}, {}, {}, "{}");'.format(
                                    line[0].strip("'\"[] "),
                                    line[1].strip("'[]\" "),
                                    str(line[2].strip("'\"[] ")),
                                    line[3].strip("'\"[] "),
                                    int(line[7].strip("'\"[] "))
                                    if line[7].strip("'\" ") != ""
                                    else "null",
                                    pegi_id,
                                    1
                                    if (line[9].strip("'\"[] ")) == "True"
                                    else 0,
                                    line[10].strip("'\"[] "),
                                )
                            )
                    except pymysql.err.IntegrityError as interr:
                        pass
                    except pymysql.err.DataError as de:
                        print(de)
                        input("Press ENTER to continue...")
                    cursor.execute(
                        'SELECT id FROM anime WHERE title LIKE "{}";'.format(
                            line[0].strip("'\"[] ")
                        )
                    )
                    anime_id = cursor.fetchone()[0]
                    for genre in line[4][1:-1].split(", "):
                        try:
                            cursor.execute("SELECT name FROM genre WHERE name LIKE \"{}\";".format(genre))
                            tmp = cursor.fetchone()
                            if tmp is None:
                                cursor.execute(
                                    'INSERT INTO genre(name) VALUES("{}");'.format(
                                        genre.strip("' ")
                                    )
                                )
                        except pymysql.err.IntegrityError as interr:
                            pass
                    for genre in line[4][1:-1].split(", "):
                        cursor.execute(
                            'SELECT id FROM genre WHERE name LIKE "{}";'.format(
                                genre.strip("' ")
                            )
                        )
                        genre_id = cursor.fetchone()[0]
                        cursor.execute(
                            "INSERT INTO has_genre(id_anime, id_genre) VALUES({},{});".format(
                                anime_id, genre_id
                            )
                        )
                    for studio in line[5][1:-1].split(", "):
                        try:
                            cursor.execute("SELECT name FROM studio WHERE name LIKE \"{}\";".format(studio.strip("\"'[] ")))
                            tmp = cursor.fetchone()
                            if tmp is None:
                                cursor.execute(
                                    'INSERT INTO studio(name) VALUES("{}");'.format(
                                        studio.strip("'[]\" ")
                                    )
                                )
                        except pymysql.err.IntegrityError as interr:
                            pass
                    for studio in line[5][1:-1].split(", "):
                        cursor.execute(
                            'SELECT id FROM studio WHERE name LIKE "{}";'.format(
                                studio.strip("'[]\" ")
                            )
                        )
                        studio_id = cursor.fetchone()[0]
                        cursor.execute(
                            "INSERT INTO has_studio(id_anime, id_studio) VALUES({}, {});".format(
                                anime_id, studio_id
                            )
                        )
                    for producer in line[6][1:-1].split(", "):
                        try:
                            cursor.execute("SELECT name FROM producer WHERE name LIKE \"{}\";".format(producer.strip("\"'[] ")))
                            tmp = cursor.fetchone()
                            if tmp is None:
                                cursor.execute(
                                    'INSERT INTO producer(name) VALUES("{}");'.format(
                                        producer.strip("[]'\" ")
                                    )
                                )
                        except pymysql.err.IntegrityError as interr:
                            pass
                    for producer in line[6][1:-1].split(", "):
                        cursor.execute(
                            'SELECT id FROM producer WHERE name LIKE "{}";'.format(
                                producer.strip("'[]\" ")
                            )
                        )
                        producer_id = cursor.fetchone()[0]
                        cursor.execute(
                            "INSERT INTO has_producer(id_anime, id_producer) VALUES({}, {});".format(
                                anime_id, producer_id
                            )
                        )
                    db.commit()
            f.close()
    db.close()
    print("Done !")
