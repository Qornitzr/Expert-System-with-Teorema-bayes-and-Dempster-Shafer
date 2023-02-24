package com.example.sistempakarteodemp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.sistempakarteodemp.entities.Account;
import com.example.sistempakarteodemp.entities.Gejala;
import com.example.sistempakarteodemp.entities.Pengetahuan;
import com.example.sistempakarteodemp.entities.Penyakit;
import com.example.sistempakarteodemp.entities.Riwayat;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DB20.db";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase myDB) {

        myDB.execSQL("CREATE TABLE IF NOT EXISTS session (id_session INTEGER PRIMARY KEY, login TEXT)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS admin (id_admin INTEGER PRIMARY KEY AUTOINCREMENT, nama_admin TEXT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS penyakit (id_penyakit INTEGER PRIMARY KEY AUTOINCREMENT, nama_penyakit TEXT NOT NULL, deskripsi TEXT, saran TEXT)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS gejala (id_gejala INTEGER PRIMARY KEY AUTOINCREMENT, nama_gejala TEXT NOT NULL, penyakit_terpilih TEXT NOT NULL)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS riwayat (id_riwayat INTEGER PRIMARY KEY AUTOINCREMENT, tanggal TEXT NOT NULL, riwayat_nama_penyakit TEXT NOT NULL, riwayat_nilai TEXT NOT NULL, riwayat_nama_gejala TEXT NOT NULL)");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS rule (id_rule INTEGER PRIMARY KEY AUTOINCREMENT, id_pen INTEGER NOT NULL , id_gej INTEGER NOT NULL , probabilitas_mb NUMERIC NOT NULL, probabilitas_md NUMERIC NOT NULL, probabilitas_total NUMERUC NOT NULL, probabilitas_bayes NUMERIC NOT NULL, FOREIGN KEY(id_pen) REFERENCES penyakit(id_penyakit), FOREIGN KEY(id_gej) REFERENCES gejala(id_gejala))");
        myDB.execSQL("INSERT INTO session (id_session, login) VALUES (1, 'kosong')");


        ContentValues contentValuesAdmin = new ContentValues();
        contentValuesAdmin.put("id_admin", "1");
        contentValuesAdmin.put("nama_admin", "Admin Nama Saya");
        contentValuesAdmin.put("username", "admin");
        contentValuesAdmin.put("password", "admin");
        myDB.insert("admin", null, contentValuesAdmin);

//        ContentValues contentValuesRiwayat = new ContentValues();
//        contentValuesRiwayat.put("id_riwayat", "1");
//        contentValuesRiwayat.put("tanggal", "2022-02-29 19:02:02");
//        contentValuesRiwayat.put("riwayat_nama_penyakit", "Big");
//        contentValuesRiwayat.put("riwayat_nilai", "90%");
//        contentValuesRiwayat.put("riwayat_nama_gejala", "itu");
//        myDB.insert("riwayat", null, contentValuesRiwayat);

        ContentValues contentValuesPenyakit = new ContentValues();
        contentValuesPenyakit.put("id_penyakit", "1");
        contentValuesPenyakit.put("nama_penyakit", "Abortus Imminens");
        contentValuesPenyakit.put("deskripsi", "Abortus imminens adalah ancaman keguguran atau dapat diartikan sebagai kondisi janin yang masih sehat namun berisiko tinggi mengalami abortus yang sesungguhnya penanganan tidak dilakukan dengan baik. Biasanya abortus imminens terjadi saat usia kehamilan kurang dari 20 minggu atau berat janin kurang dari 500 gram. " +
                "Gejala utama yang terjadi saat ibu hamil mengalami abortus imminens merupakan pendarahan dan umumnya disertai keluhan lain seperti kram pada perut bagian kanan bawah. Pendarahan terjadi saat kehamilan berusia kurang dari 20 minggu. Lalu, yang keluar saat pendarahan hanya darah tidak disertai oleh jaringan lain hasil dari konsepsi.\n");
        contentValuesPenyakit.put("saran", "1.\tBedrest atau Istirahat Total\n" +
                "\tIstirahat total dilakukan hingga pendarahan yang terjadi akibat abortus imminens benar-benar berhenti. Setelah berhenti, jangan langsung melakukan aktivitas fisik. Lakukan aktivitas yang ringan terlebih dahulu seperti duduk, mulai berjalan pelan, hingga pasti tidak terjadi pendarahan lagi selama 24 jam ke depan.\n" +
                "2.\tObat-Obatan\n" +
                "\tSelain bedrest, untuk menghentikan pendarahan dapat ditunjang dengan obat-obatan golongan spasmolitik yang berguna untuk mencegah kontraksi.\n" +
                "3.\tSuplai Hormon Progesteron dalam Tubuh\n" +
                "\tHormon progesteron diberikan dokter kepada pasien yang mengalami abortus imminen jika kadar hormon kurang dari yang dibutuhkan ibu hamil.\n" +
                "4.\tTerapi Suportif\n" +
                "\tTerapi ini didukung oleh keluarga dan orang terdekat agar ibu hamil yang mengalami abortus imminens dapat lebih tenang dan tidak stres.\n" +
                "\tIbu hamil yang mengalami abortus imminens biasanya disarankan untuk dirawat di rumah sakit selama kurang lebih 2–3 hari hingga pendarahan sungguh-sungguh berhenti. Selain itu, berhubungan intim juga tidak diperbolehkan sekitar 2 minggu setelah pengobatan selesai.\n");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "2");
        contentValuesPenyakit.put("nama_penyakit", "Abortus Insipiens");
        contentValuesPenyakit.put("deskripsi", "Abortus insipiens disebut juga dengan keguguran yang tidak bisa dihindari. Pada keguguran jenis ini, janin masih utuh di dalam rahim, tetapi ibu hamil sudah mengalami perdarahan dan pembukaan jalan lahir sehingga keguguran pasti terjadi. " +
                "Pada abortus insipiens, biasanya terjadi perdarahan yang sangat banyak, tetapi belum ada gumpalan jaringan janin. Selain itu, gejala lain yang mungkin dirasakan oleh ibu hamil saat terjadi abortus insipiens adalah kram perut yang hebat.\n");
        contentValuesPenyakit.put("saran", "1.\tKonsumsi makanan bergizi seimbang.\n" +
                "2.\tKonsumsi suplemen asam folat.\n" +
                "3.\tJaga berat badan ideal.\n" +
                "4.\tLakukan olahraga secara rutin.\n" +
                "5.\tJangan merokok dan jauhi paparan asap rokok.\n" +
                "6.\tHindari konsumsi minuman beralkohol dan obat-obatan terlarang.\n" +
                "7.\tLakukan vaksinasi untuk menghindari berbagai penyakit infeksi.\n");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "3");
        contentValuesPenyakit.put("nama_penyakit", "Abortus Inkompletus");
        contentValuesPenyakit.put("deskripsi", "Abortus inkomplit adalah salah satu jenis keguguran yang terjadi pada usia kehamilan kurang dari 20 minggu. Ketika ini terjadi, jaringan janin yang telah mati tidak keluar sepenuhnya dari rahim dan menyebabkan perdarahan terus berlanjut.\n" +
                "\n" +
                "Pasien dengan abortus inkomplit umumnya mengalami perdarahan hebat dan nyeri perut. Kondisi ini juga ditandai dengan selaput ketuban pecah serta keadaan mulut rahim terbuka.\n");
        contentValuesPenyakit.put("saran", "1.\tMenunggu sisa janin keluar secara alami\n" +
                "\tSisa janin dapat keluar secara alami dari rahim dalam waktu 1–2 minggu. Namun, selama menunggu, perdarahan bisa saja sangat banyak dan tidak kunjung berhenti bahkan saat sudah mendekati 2 minggu. Oleh karena itu, kebanyakan dokter biasanya lebih menyarankan metode penanganan lain.\n" +
                "2.\tMenggunakan obat\n" +
                "\tDokter akan memberikan obat untuk mempercepat proses pengeluaran sisa jaringan janin dari rahim. Tingkat keberhasilan cara ini cukup tinggi, yaitu hingga 80–99%, terutama pada kehamilan yang masih di trimester pertama. Obat tersebut dapat digunakan dengan cara diminum atau dimasukkan ke dalam vagina. Efek samping yang mungkin dirasakan oleh pasien adalah mual, muntah, atau diare.\n" +
                "3.\tMenjalani kuret\n" +
                "Dilatasi dan kuretase, atau yang lebih sering disebut kuret, merupakan metode penanganan abortus inklomplit yang paling aman dan efektif. Pada prosedur ini, leher rahim dilebarkan dan sisa jaringan yang ada di dalam rahim diangkat.\n" +
                "Kuret biasanya disarankan jika dokter menganggap pasien memerlukan penanganan segera untuk menghentikan perdarahan dan mencegah infeksi yang dapat mengancam nyawanya.\n");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "4");
        contentValuesPenyakit.put("nama_penyakit", "Abortus Kompletus");
        contentValuesPenyakit.put("deskripsi", "Pada jenis keguguran ini, mulut rahim terbuka lebar dan seluruh jaringan janin keluar dari rahim. Ketika abortus komplet terjadi, Ibu hamil akan mengalami perdarahan vagina serta nyeri perut seperti sedang melahirkan. Biasanya, abortus komplet terjadi pada usia kehamilan kurang dari 12 minggu.\n" +
                "\n" +
                "Abortus Kompletus di bagi menjadi tiga yaitu:\n" +
                "•\tAbortus Komplit Disengaja (Aborsi)\n" +
                "•\tAbortus Komplit Tanpa Disengaja (Keguguran Alami)\n" +
                "•\tMenunggu Keguguran Alami atau Abortus Komplit Tanpa Disengaja\n");
        contentValuesPenyakit.put("saran", "Segera lakukan pengecekan ke rumah sakit kepada dokter spesialis atau bidan untuk mendapatkan menangan pada penyakit ini.");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "5");
        contentValuesPenyakit.put("nama_penyakit", "Abortus Infeksiosus/Septik");
        contentValuesPenyakit.put("deskripsi", "Aborus infeksius adalah keguguran yang disertai dengan infeksi genital. " +
                "Abortus septik adalah keguguran yang disertai dengan infeksi berat, penyebaran kuman sampai peredaran darah/ peritonium.\n");
        contentValuesPenyakit.put("saran", "Segera lakukan pengecekan ke rumah sakit kepada dokter spesialis atau bidan untuk mendapatkan menangan pada penyakit ini.");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "6");
        contentValuesPenyakit.put("nama_penyakit", "Missed Abortion");
        contentValuesPenyakit.put("deskripsi", "Missed abortion atau keguguran tanpa gejala ini cukup membuat ibu menjadi syok karena tidak ada tanda-tanda keguguran pada umumnya seperti nyeri perut juga perdarahan. Keguguran tanpa gejala atau terlewat ini terjadi saat janin tidak mengalami perkembangan hingga meninggal dalam kandungan dan tetap berada dalam rahim. Perlu orangtua ketahui bahwa sekitar 10% kehamilan dapat mengalami keguguran. Lalu, sekitar 80% keguguran pun terjadi pada trimester pertama. \n" +
                "Seperti pada penjelasan di atas, missed abortion atau missed misscarriade ini tidak memperlihatkan gejala apa pun. Pada sebagian orang, mungkin hanya akan mengalami keluarnya bercak atau flek, tetapi bisa jadi tidak ada sama sekali. Lalu, pada sebagian kasus keguguran tanpa gejala ini pun ibu masih merasakan keluhan saat hamil karena plasenta masih memproduksi hormon kehamilan. Sebagai contoh, nyeri payudara, mual atau morning sickness, hingga rasa lelah yang tidak biasa. Ibu juga perlu memahami beberapa tanda atau gejala keguguran pada umumnya, seperti:\n" +
                "•\tperdarahan vagina,\n" +
                "•\tkram perut,\n" +
                "•\tkeluarnya cairan serta jaringan plasenta, hingga\n" +
                "•\tkurangnya gejala kehamilan.\n" +
                "Konsultasikan kembali dengan dokter mengenai gejala atau tanda missed abortion karena setiap orang mempunyai kondisi kesehatan yang berbeda. Tidak menutup kemungkinan paparan tanda atau gejala tersebut pun berbeda.\n");
        contentValuesPenyakit.put("saran", "1.\tExpectant management\n" +
                "\t Ini adalah pilihan untuk menunggu agar janin atau embrio dalam rahim Anda keluar secara alami. Sekitar 65% wanita berhasil melakukan cara ini. " +
                "Jika embrio tidak juga keluar pada waktu menunggu yang sudah dokter tentukan, kemungkinan Anda perlu pengobatan atau pembedahan.\n" +
                "2.\t Medical management\n" +
                "Pilihan lainnya saat cara alami belum berhasil adalah mengonsumsi obat seperti misoprostol. Obat ini dapat membantu memicu tubuh untuk mengeluarkan jaringan dari missed abortion. " +
                "Pemberian obat harus sesuai dengan resep dokter. Setelah itu, Anda juga bisa memilih untuk pulang ke rumah atau tinggal di rumah sakit.\n" +
                "3.\tSurgical management\n" +
                "\t Pembedahan adalah pilihan terakhir jika dua pilihan lainnya tidak berhasil untuk membersihkan sisa embrio yang masih ada dalam tubuh Anda. " +
                "Tindakan yang akan dokter lakukan adalah dilation & curettage (kuret) yang berfungsi untuk mengangkat jaringan yang tersisa dalam rahim. " +
                "Selama 1 – 2 minggu, setelah sisa jaringan telah keluar sepenuhnya, dokter tidak menyarankan untuk memasukkan apa pun ke dalam vagina untuk mencegah infeksi. " +
                "Apa pun jenis kegugurannya, kondisi missed abortion ini juga bisa mengakibatkan trauma tersendiri bagi orangtua terutama pada ibu. " +
                "Apabila Anda membutuhkan bantuan dukungan selain dari keluarga dan orang-orang terdekat, tidak ada salahnya untuk melakukan konseling.");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "7");
        contentValuesPenyakit.put("nama_penyakit", "Mola Hidatidosa");
        contentValuesPenyakit.put("deskripsi", "Mola hydatidosa atau hamil anggur adalah pembentukan ari-ari (plasenta) yang tidak normal pada masa kehamilan. Kondisi ini tergolong komplikasi kehamilan yang jarang terjadi.");
        contentValuesPenyakit.put("saran", "Hamil anggur terjadi karena kesalahan gen dalam proses pembuahan. Oleh sebab itu, kondisi ini sulit dicegah. Kendati demikian, ada beberapa upaya yang bisa dilakukan pada penderita hamil anggur untuk mengurangi kemungkinan terjadinya kondisi serupa di kehamilan selanjutnya. " +
                "Salah satu upaya untuk mengurangi risiko hamil anggur terjadi kembali adalah menunda kehamilan, setidaknya 1 tahun setelah kuret. Untuk mencegah kehamilan, Anda bisa menggunakan jenis alat kontrasepsi mana pun, kecuali KB spiral. " +
                "Pemeriksaan rutin ke dokter kandungan perlu dilakukan jika hamil kembali setelah hamil anggur. Tujuannya adalah untuk memastikan pertumbuhan plasenta dan janin berlangsung dengan normal.\n");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "8");
        contentValuesPenyakit.put("nama_penyakit", "Kehamilan Ektopik");
        contentValuesPenyakit.put("deskripsi", "Kehamilan ektopik adalah kehamilan yang terjadi di luar rahim. Tergantung lokasi menempelnya sel telur, gejala kehamilan ektopik dapat menyerupai gejala pada penyakit usus buntu. Apabila tidak segera ditangani, kehamilan ektopik dapat berakibat fatal bagi ibu.\n" +
                "\n" +
                "Kehamilan berawal dari sel telur yang telah dibuahi oleh sel sperma. Pada proses kehamilan normal, sel telur yang telah dibuahi akan menetap di saluran indung telur (tuba falopi) sebelum dilepaskan ke rahim. Selanjutnya, sel telur akan menempel di rahim dan terus berkembang hingga masa persalinan tiba.\n");
        contentValuesPenyakit.put("saran", "Segera periksakan diri ke dokter bila Anda mengalami gejala di atas, terutama bila Anda mengetahui sedang hamil tetapi masih menggunakan alat kontrasepsi. Anda juga dianjurkan untuk segera memeriksakan diri bila mengalami sejumlah keluhan berikut:\n" +
                "•\tNyeri hebat di bagian panggul, bahu, atau leher\n" +
                "•\tNyeri di salah satu sisi bagian bawah perut yang memburuk seiring waktu\n" +
                "•\tPerdarahan ringan hingga berat dari vagina, dengan warna darah yang bisa lebih gelap dari darah menstruasi\n" +
                "•\tPusing atau lemas\n\n" +
                "Pencegahan Kehamilan Ektopik\n" +
                "Tidak ada cara untuk mencegah kehamilan ektopik. Meski demikian, ada upaya yang bisa dilakukan untuk menurunkan risiko terjadinya hamil di luar kandungan pada kehamilan berikutnya, antara lain:\n" +
                "•\tBerhenti merokok\n" +
                "•\tMenjaga berat badan ideal\n" +
                "•\tMenghindari perilaku yang meningkatkan risiko terkena penyakit menular seksual\n" +
                "•\tMenjalani pemeriksaan kesehatan kandungan secara rutin\n");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        contentValuesPenyakit.put("id_penyakit", "9");
        contentValuesPenyakit.put("nama_penyakit", "Blighted Ovum");
        contentValuesPenyakit.put("deskripsi", "Blighted ovum atau hamil kosong adalah kehamilan yang tidak mengandung embrio. Dalam dunia medis, blighted ovum juga dikenal dengan istilah anembryonic gestation. Diperkirakan setengah dari seluruh kasus keguguran di trimester pertama kehamilan disebabkan oleh kondisi ini.\n" +
                "\n" +
                "Blighted ovum biasanya terjadi akibat kelainan kromosom. Kelainan kromosom itu sendiri dapat disebabkan oleh pembelahan sel yang tidak sempurna serta kualitas sel telur dan sperma yang buruk.\n");
        contentValuesPenyakit.put("saran", "Ibu yang sedang hamil dianjurkan untuk melakukan pemeriksaan kehamilan secara rutin. Berikut ini adalah jadwal pemeriksaan yang disarankan:\n" +
                "•\tTrimester pertama (minggu ke-4 hingga ke-28): 1 bulan sekali\n" +
                "•\tTrimester kedua (minggu ke-28 hingga ke-36): 2 minggu sekali\n" +
                "•\tTrimester ketiga (minggu ke-36 hingga ke-40): 1 minggu sekali\n\n" +
                "Segera ke dokter jika Anda mengalami gejala-gejala seperti yang disebutkan di atas. Perdarahan pada trimester pertama tidak selalu menandakan terjadinya keguguran. Namun, pemeriksaan tetap perlu dilakukan agar dokter dapat mengetahui penyebabnya dan menentukan penanganan yang tepat.\n" +
                "Pemeriksaan juga perlu dilakukan jika Anda pernah mengalami hamil kosong pada kehamilan sebelumnya dan ingin merencanakan kehamilan. Hal ini perlu dilakukan untuk mencegah berulangnya kondisi yang sama.\n" +
                "Pencegahan Blighted Ovum\n" +
                "Pada sebagian besar kasus, blighted ovum tidak dapat dicegah. Pemeriksaan rutin ke dokter selama kehamilan merupakan cara terbaik untuk memantau kondisi ibu dan janin.\n" +
                "Selain itu, ada beberapa tes yang dapat dilakukan untuk mendeteksi faktor yang dapat meningkatkan risiko terjadinya blighted ovum. Tes tersebut antara lain:\n" +
                "•\tPemeriksaan genetik pra-implantasi (PGT), untuk memeriksa kondisi genetik embrio sebelum terjadinya implantasi embrio ke dalam rahim\n" +
                "•\tAnalisis sperma, untuk memeriksa kualitas sperma\n" +
                "•\tTes hormon FSH (hormon perangsang folikel) atau tes hormon AHM (anti-mullerian hormone), untuk mengukur kadar kedua hormon tersebut di dalam tubuh sehingga bisa dijadikan acuan perlu tidaknya tindakan untuk meningkatkan kualitas sel telur\n");
        myDB.insert("penyakit", null, contentValuesPenyakit);

        ContentValues contentValuesGejala = new ContentValues();
        contentValuesGejala.put("id_gejala", "1");
        contentValuesGejala.put("nama_gejala", "Apakah besar rahim tidak sesuai dengan umur kehamilan (tidak berkembang)?");
        contentValuesGejala.put("penyakit_terpilih", "6");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "2");
        contentValuesGejala.put("nama_gejala", "Apakah cairan/jaringan berbau busuk?");
        contentValuesGejala.put("penyakit_terpilih", "5");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "3");
        contentValuesGejala.put("nama_gejala", "Apakah gejala perdarahan antara rata-rata usia kehamilan 12-14 minggu?");
        contentValuesGejala.put("penyakit_terpilih", "7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "4");
        contentValuesGejala.put("nama_gejala", "Apakah hasil konsepsi masih baik dalam kandungan?");
        contentValuesGejala.put("penyakit_terpilih", "1");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "5");
        contentValuesGejala.put("nama_gejala", "Apakah hasil konsepsi masih berada dalam kavum uteri dan dalam proses pengeluaran?");
        contentValuesGejala.put("penyakit_terpilih", "2");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "6");
        contentValuesGejala.put("nama_gejala", "Apakah ketika hasil pemeriksaan usg adalah janin tidak berkembang?");
        contentValuesGejala.put("penyakit_terpilih", "9");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "7");
        contentValuesGejala.put("nama_gejala", "Apakah janin telah mati dalam kandungan selama 6-8 minggu tapi belum keluar?");
        contentValuesGejala.put("penyakit_terpilih", "6");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "8");
        contentValuesGejala.put("nama_gejala", "Apakah kehamilan tidak dapat dipertahankan?");
        contentValuesGejala.put("penyakit_terpilih", "2");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "9");
        contentValuesGejala.put("nama_gejala", "Apakah keluarnya gelembung-gelembung putih/bening dari vagina?");
        contentValuesGejala.put("penyakit_terpilih", "7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "10");
        contentValuesGejala.put("nama_gejala", "Apakah terasa mual atau muntah yang tidak begitu dirasakan?");
        contentValuesGejala.put("penyakit_terpilih", "9");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "11");
        contentValuesGejala.put("nama_gejala", "Apakah terasa mual hebat atau muntah hebat?");
        contentValuesGejala.put("penyakit_terpilih", "7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "12");
        contentValuesGejala.put("nama_gejala", "Apakah terasa mulas karena kontraksi yang sering dan kuat?");
        contentValuesGejala.put("penyakit_terpilih", "2");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "13");
        contentValuesGejala.put("nama_gejala", "Apakah terasa mulas sedikit?");
        contentValuesGejala.put("penyakit_terpilih", "1");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "14");
        contentValuesGejala.put("nama_gejala", "Apakah mulut rahim/cervix masih dalam kondisi menutup?");
        contentValuesGejala.put("penyakit_terpilih", "1");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "15");
        contentValuesGejala.put("nama_gejala", "Apakah mulut rahim/cervix terbuka?");
        contentValuesGejala.put("penyakit_terpilih", "2, 3");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "16");
        contentValuesGejala.put("nama_gejala", "Apakah mulut rahim/cervix tertutup?");
        contentValuesGejala.put("penyakit_terpilih", "4");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "17");
        contentValuesGejala.put("nama_gejala", "Apakah terasa nyeri lebih kuat?");
        contentValuesGejala.put("penyakit_terpilih", "2, 3");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "18");
        contentValuesGejala.put("nama_gejala", "Apakah terasa nyeri perut bagian bawah?");
        contentValuesGejala.put("penyakit_terpilih", "1, 8, 9");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "19");
        contentValuesGejala.put("nama_gejala", "Apakah terasa nyeri perut berkurang?");
        contentValuesGejala.put("penyakit_terpilih", "4, 6");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "20");
        contentValuesGejala.put("nama_gejala", "Apakah terasa nyeri tekan menggigil?");
        contentValuesGejala.put("penyakit_terpilih", "5");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "21");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perdarahan hebat?");
        contentValuesGejala.put("penyakit_terpilih", "3");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "22");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perdarahan lebih banyak/sesuai umur kehamilan?");
        contentValuesGejala.put("penyakit_terpilih", "2");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "23");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perdarahan pervaginam?");
        contentValuesGejala.put("penyakit_terpilih", "1, 5, 9");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "24");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perdarahan sedikit-sedikit lalu sekaligus banyak?");
        contentValuesGejala.put("penyakit_terpilih", "7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "25");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perdarahan sedikit-sedikit lalu berkurang?");
        contentValuesGejala.put("penyakit_terpilih", "4, 6");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "26");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perdarahan tidak banyak dan berwarna coklat tua?");
        contentValuesGejala.put("penyakit_terpilih", "8");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "27");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi perkembangan lebih pesat, sehingga umumnya uterus/rahim lebih besar dari usia kehamilan?");
        contentValuesGejala.put("penyakit_terpilih", "7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "28");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi pusing hebat?");
        contentValuesGejala.put("penyakit_terpilih", "7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "29");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi sakit perut/nyeri mendadak yang kemudian dususul syok atau pingsan?");
        contentValuesGejala.put("penyakit_terpilih", "8");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "30");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi sebagian hasil konsepsi telah keluar dan masih ada yang tertinggal? ");
        contentValuesGejala.put("penyakit_terpilih", "3");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "31");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi seluruh hasil konsepsi telah dikeluarkan?");
        contentValuesGejala.put("penyakit_terpilih", "4");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "32");
        contentValuesGejala.put("nama_gejala", "Apakah sering menyebabkan syok?");
        contentValuesGejala.put("penyakit_terpilih", "3, 5, 7");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "33");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi panas tinggi?");
        contentValuesGejala.put("penyakit_terpilih", "5");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "34");
        contentValuesGejala.put("nama_gejala", "Apakah terjadi takikardi/jantung berdebar-debar/denyut jantung cepat?");
        contentValuesGejala.put("penyakit_terpilih", "5");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "35");
        contentValuesGejala.put("nama_gejala", "Apakah tampak sakit dan lelah?");
        contentValuesGejala.put("penyakit_terpilih", "5");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "36");
        contentValuesGejala.put("nama_gejala", "Apakah teraba jaringan kehamilan di mulut rahim?");
        contentValuesGejala.put("penyakit_terpilih", "2, 3");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "37");
        contentValuesGejala.put("nama_gejala", "Apakah tes kehamilan/hcg masih positif?");
        contentValuesGejala.put("penyakit_terpilih", "1, 2, 3, 5, 8, 9");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "38");
        contentValuesGejala.put("nama_gejala", "Apakah tes kehamilan/hcg masih positif sampai 7-10 hari setelah abortus?");
        contentValuesGejala.put("penyakit_terpilih", "4");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "39");
        contentValuesGejala.put("nama_gejala", "Apakah tes kehamilan/hcg negatif?");
        contentValuesGejala.put("penyakit_terpilih", "6");
        myDB.insert("gejala", null, contentValuesGejala);

        contentValuesGejala.put("id_gejala", "40");
        contentValuesGejala.put("nama_gejala", "Apakah uterus/rahim mengecil?");
        contentValuesGejala.put("penyakit_terpilih", "4");
        myDB.insert("gejala", null, contentValuesGejala);


// Awal
        ContentValues contentValuesPengetahuan = new ContentValues();
        contentValuesPengetahuan.put("id_rule", "1");
        contentValuesPengetahuan.put("id_pen", "1");
        contentValuesPengetahuan.put("id_gej", "23");
        contentValuesPengetahuan.put("probabilitas_mb", "0.74");
        contentValuesPengetahuan.put("probabilitas_md", "0.26");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "2");
        contentValuesPengetahuan.put("id_pen", "1");
        contentValuesPengetahuan.put("id_gej", "4");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "3");
        contentValuesPengetahuan.put("id_pen", "1");
        contentValuesPengetahuan.put("id_gej", "13");
        contentValuesPengetahuan.put("probabilitas_mb", "0.40");
        contentValuesPengetahuan.put("probabilitas_md", "0.60");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "4");
        contentValuesPengetahuan.put("id_pen", "1");
        contentValuesPengetahuan.put("id_gej", "37");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "5");
        contentValuesPengetahuan.put("id_pen", "1");
        contentValuesPengetahuan.put("id_gej", "14");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "6");
        contentValuesPengetahuan.put("id_pen", "1");
        contentValuesPengetahuan.put("id_gej", "18");
        contentValuesPengetahuan.put("probabilitas_mb", "0.54");
        contentValuesPengetahuan.put("probabilitas_md", "0.46");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "7");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "15");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "8");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "37");
        contentValuesPengetahuan.put("probabilitas_mb", "0.9");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "9");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "5");
        contentValuesPengetahuan.put("probabilitas_mb", "0.9");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "10");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "12");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "11");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "8");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "12");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "36");
        contentValuesPengetahuan.put("probabilitas_mb", "0.70");
        contentValuesPengetahuan.put("probabilitas_md", "0.30");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "13");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "22");
        contentValuesPengetahuan.put("probabilitas_mb", "0.6");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "14");
        contentValuesPengetahuan.put("id_pen", "2");
        contentValuesPengetahuan.put("id_gej", "17");
        contentValuesPengetahuan.put("probabilitas_mb", "0.50");
        contentValuesPengetahuan.put("probabilitas_md", "0.50");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "15");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "37");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "16");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "15");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "17");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "32");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "18");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "21");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "19");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "36");
        contentValuesPengetahuan.put("probabilitas_mb", "0.70");
        contentValuesPengetahuan.put("probabilitas_md", "0.30");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "20");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "30");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "21");
        contentValuesPengetahuan.put("id_pen", "3");
        contentValuesPengetahuan.put("id_gej", "17");
        contentValuesPengetahuan.put("probabilitas_mb", "0.50");
        contentValuesPengetahuan.put("probabilitas_md", "0.50");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "22");
        contentValuesPengetahuan.put("id_pen", "4");
        contentValuesPengetahuan.put("id_gej", "16");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "23");
        contentValuesPengetahuan.put("id_pen", "4");
        contentValuesPengetahuan.put("id_gej", "25");
        contentValuesPengetahuan.put("probabilitas_mb", "0.90");
        contentValuesPengetahuan.put("probabilitas_md", "0.10");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "24");
        contentValuesPengetahuan.put("id_pen", "4");
        contentValuesPengetahuan.put("id_gej", "31");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "25");
        contentValuesPengetahuan.put("id_pen", "4");
        contentValuesPengetahuan.put("id_gej", "19");
        contentValuesPengetahuan.put("probabilitas_mb", "0.70");
        contentValuesPengetahuan.put("probabilitas_md", "0.30");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "26");
        contentValuesPengetahuan.put("id_pen", "4");
        contentValuesPengetahuan.put("id_gej", "38");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "27");
        contentValuesPengetahuan.put("id_pen", "4");
        contentValuesPengetahuan.put("id_gej", "40");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "28");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "23");
        contentValuesPengetahuan.put("probabilitas_mb", "0.74");
        contentValuesPengetahuan.put("probabilitas_md", "0.26");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "29");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "37");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "30");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "32");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "31");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "33");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "32");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "35");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "33");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "34");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "34");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "2");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "35");
        contentValuesPengetahuan.put("id_pen", "5");
        contentValuesPengetahuan.put("id_gej", "20");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "36");
        contentValuesPengetahuan.put("id_pen", "6");
        contentValuesPengetahuan.put("id_gej", "25");
        contentValuesPengetahuan.put("probabilitas_mb", "0.90");
        contentValuesPengetahuan.put("probabilitas_md", "0.10");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "37");
        contentValuesPengetahuan.put("id_pen", "6");
        contentValuesPengetahuan.put("id_gej", "19");
        contentValuesPengetahuan.put("probabilitas_mb", "0.70");
        contentValuesPengetahuan.put("probabilitas_md", "0.30");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "38");
        contentValuesPengetahuan.put("id_pen", "6");
        contentValuesPengetahuan.put("id_gej", "1");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "39");
        contentValuesPengetahuan.put("id_pen", "6");
        contentValuesPengetahuan.put("id_gej", "7");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "40");
        contentValuesPengetahuan.put("id_pen", "6");
        contentValuesPengetahuan.put("id_gej", "39");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "41");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "11");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "42");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "28");
        contentValuesPengetahuan.put("probabilitas_mb", "0.40");
        contentValuesPengetahuan.put("probabilitas_md", "0.60");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "43");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "27");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "44");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "24");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "45");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "32");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "46");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "9");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "47");
        contentValuesPengetahuan.put("id_pen", "7");
        contentValuesPengetahuan.put("id_gej", "3");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "48");
        contentValuesPengetahuan.put("id_pen", "8");
        contentValuesPengetahuan.put("id_gej", "37");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "49");
        contentValuesPengetahuan.put("id_pen", "8");
        contentValuesPengetahuan.put("id_gej", "18");
        contentValuesPengetahuan.put("probabilitas_mb", "0.54");
        contentValuesPengetahuan.put("probabilitas_md", "0.46");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "50");
        contentValuesPengetahuan.put("id_pen", "8");
        contentValuesPengetahuan.put("id_gej", "29");
        contentValuesPengetahuan.put("probabilitas_mb", "0.80");
        contentValuesPengetahuan.put("probabilitas_md", "0.20");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "51");
        contentValuesPengetahuan.put("id_pen", "8");
        contentValuesPengetahuan.put("id_gej", "26");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "52");
        contentValuesPengetahuan.put("id_pen", "9");
        contentValuesPengetahuan.put("id_gej", "23");
        contentValuesPengetahuan.put("probabilitas_mb", "0.74");
        contentValuesPengetahuan.put("probabilitas_md", "0.26");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "53");
        contentValuesPengetahuan.put("id_pen", "9");
        contentValuesPengetahuan.put("id_gej", "18");
        contentValuesPengetahuan.put("probabilitas_mb", "0.54");
        contentValuesPengetahuan.put("probabilitas_md", "0.46");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "54");
        contentValuesPengetahuan.put("id_pen", "9");
        contentValuesPengetahuan.put("id_gej", "37");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "55");
        contentValuesPengetahuan.put("id_pen", "9");
        contentValuesPengetahuan.put("id_gej", "6");
        contentValuesPengetahuan.put("probabilitas_mb", "1.0");
        contentValuesPengetahuan.put("probabilitas_md", "0.0");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

        contentValuesPengetahuan.put("id_rule", "56");
        contentValuesPengetahuan.put("id_pen", "9");
        contentValuesPengetahuan.put("id_gej", "10");
        contentValuesPengetahuan.put("probabilitas_mb", "0.60");
        contentValuesPengetahuan.put("probabilitas_md", "0.40");
        contentValuesPengetahuan.put("probabilitas_total", "0");
        contentValuesPengetahuan.put("probabilitas_bayes", "0");
        myDB.insert("rule", null, contentValuesPengetahuan);

//        ContentValues contentValuesPengetahuan = new ContentValues();
//        contentValuesPengetahuan.put("id_rule", "1");
//        contentValuesPengetahuan.put("id_pen", "1");
//        contentValuesPengetahuan.put("id_gej", "23");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.17");
//        contentValuesPengetahuan.put("probabilitas_md", "0.8");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "2");
//        contentValuesPengetahuan.put("id_pen", "1");
//        contentValuesPengetahuan.put("id_gej", "4");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.18");
//        contentValuesPengetahuan.put("probabilitas_md", "0.82");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "3");
//        contentValuesPengetahuan.put("id_pen", "1");
//        contentValuesPengetahuan.put("id_gej", "13");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.11");
//        contentValuesPengetahuan.put("probabilitas_md", "0.89");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "4");
//        contentValuesPengetahuan.put("id_pen", "1");
//        contentValuesPengetahuan.put("id_gej", "37");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.21");
//        contentValuesPengetahuan.put("probabilitas_md", "0.8");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "5");
//        contentValuesPengetahuan.put("id_pen", "1");
//        contentValuesPengetahuan.put("id_gej", "14");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.2");
//        contentValuesPengetahuan.put("probabilitas_md", "0.8");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "6");
//        contentValuesPengetahuan.put("id_pen", "1");
//        contentValuesPengetahuan.put("id_gej", "18");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.89");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "7");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "15");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1375");
//        contentValuesPengetahuan.put("probabilitas_md", "0.86");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "8");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "37");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.21");
//        contentValuesPengetahuan.put("probabilitas_md", "0.86");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "9");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "5");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.12");
//        contentValuesPengetahuan.put("probabilitas_md", "0.88");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "10");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "12");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.12");
//        contentValuesPengetahuan.put("probabilitas_md", "0.88");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "11");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "8");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.14");
//        contentValuesPengetahuan.put("probabilitas_md", "0.86");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "12");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "36");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1275");
//        contentValuesPengetahuan.put("probabilitas_md", "0.88");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "13");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "22");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.11");
//        contentValuesPengetahuan.put("probabilitas_md", "0.89");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "14");
//        contentValuesPengetahuan.put("id_pen", "2");
//        contentValuesPengetahuan.put("id_gej", "17");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1075");
//        contentValuesPengetahuan.put("probabilitas_md", "0.89");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "15");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "37");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.21");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "16");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "15");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1375");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "17");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "32");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.2");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "18");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "21");
//        contentValuesPengetahuan.put("probabilitas_mb", "105");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "19");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "36");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1275");
//        contentValuesPengetahuan.put("probabilitas_md", "0.30");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "20");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "30");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.135");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "21");
//        contentValuesPengetahuan.put("id_pen", "3");
//        contentValuesPengetahuan.put("id_gej", "17");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1075");
//        contentValuesPengetahuan.put("probabilitas_md", "0.50");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "22");
//        contentValuesPengetahuan.put("id_pen", "4");
//        contentValuesPengetahuan.put("id_gej", "16");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.18");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "23");
//        contentValuesPengetahuan.put("id_pen", "4");
//        contentValuesPengetahuan.put("id_gej", "25");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.185");
//        contentValuesPengetahuan.put("probabilitas_md", "0.10");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "24");
//        contentValuesPengetahuan.put("id_pen", "4");
//        contentValuesPengetahuan.put("id_gej", "31");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.18");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "25");
//        contentValuesPengetahuan.put("id_pen", "4");
//        contentValuesPengetahuan.put("id_gej", "19");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.145");
//        contentValuesPengetahuan.put("probabilitas_md", "0.30");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "26");
//        contentValuesPengetahuan.put("id_pen", "4");
//        contentValuesPengetahuan.put("id_gej", "38");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.18");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "27");
//        contentValuesPengetahuan.put("id_pen", "4");
//        contentValuesPengetahuan.put("id_gej", "40");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.14");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "28");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "23");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.17");
//        contentValuesPengetahuan.put("probabilitas_md", "0.26");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "29");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "37");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.21");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "30");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "32");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.2");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "31");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "33");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1125");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "32");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "35");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1125");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "33");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "34");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.3");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "34");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "2");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.3");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "35");
//        contentValuesPengetahuan.put("id_pen", "5");
//        contentValuesPengetahuan.put("id_gej", "20");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1125");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "36");
//        contentValuesPengetahuan.put("id_pen", "6");
//        contentValuesPengetahuan.put("id_gej", "25");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.185");
//        contentValuesPengetahuan.put("probabilitas_md", "0.10");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "37");
//        contentValuesPengetahuan.put("id_pen", "6");
//        contentValuesPengetahuan.put("id_gej", "19");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.145");
//        contentValuesPengetahuan.put("probabilitas_md", "0.30");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "38");
//        contentValuesPengetahuan.put("id_pen", "6");
//        contentValuesPengetahuan.put("id_gej", "1");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.22");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "39");
//        contentValuesPengetahuan.put("id_pen", "6");
//        contentValuesPengetahuan.put("id_gej", "7");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.22");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "40");
//        contentValuesPengetahuan.put("id_pen", "6");
//        contentValuesPengetahuan.put("id_gej", "39");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.22");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "41");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "11");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.18");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "42");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "28");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.1");
//        contentValuesPengetahuan.put("probabilitas_md", "0.60");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "43");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "27");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "44");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "24");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.12");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "45");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "32");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.2");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "46");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "9");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "47");
//        contentValuesPengetahuan.put("id_pen", "7");
//        contentValuesPengetahuan.put("id_gej", "3");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "48");
//        contentValuesPengetahuan.put("id_pen", "8");
//        contentValuesPengetahuan.put("id_gej", "37");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.21");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "49");
//        contentValuesPengetahuan.put("id_pen", "8");
//        contentValuesPengetahuan.put("id_gej", "18");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.46");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "50");
//        contentValuesPengetahuan.put("id_pen", "8");
//        contentValuesPengetahuan.put("id_gej", "29");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.25");
//        contentValuesPengetahuan.put("probabilitas_md", "0.20");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "51");
//        contentValuesPengetahuan.put("id_pen", "8");
//        contentValuesPengetahuan.put("id_gej", "26");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "52");
//        contentValuesPengetahuan.put("id_pen", "9");
//        contentValuesPengetahuan.put("id_gej", "23");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.17");
//        contentValuesPengetahuan.put("probabilitas_md", "0.26");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "53");
//        contentValuesPengetahuan.put("id_pen", "9");
//        contentValuesPengetahuan.put("id_gej", "18");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.15");
//        contentValuesPengetahuan.put("probabilitas_md", "0.46");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "54");
//        contentValuesPengetahuan.put("id_pen", "9");
//        contentValuesPengetahuan.put("id_gej", "37");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.21");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "55");
//        contentValuesPengetahuan.put("id_pen", "9");
//        contentValuesPengetahuan.put("id_gej", "6");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.25");
//        contentValuesPengetahuan.put("probabilitas_md", "0.0");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);
//
//        contentValuesPengetahuan.put("id_rule", "56");
//        contentValuesPengetahuan.put("id_pen", "9");
//        contentValuesPengetahuan.put("id_gej", "10");
//        contentValuesPengetahuan.put("probabilitas_mb", "0.2");
//        contentValuesPengetahuan.put("probabilitas_md", "0.40");
//        contentValuesPengetahuan.put("probabilitas_total", "0");
//        contentValuesPengetahuan.put("probabilitas_bayes", "0");
//        myDB.insert("rule", null, contentValuesPengetahuan);


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS penyakit");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS gejala");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS rule");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS admin");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS session");
        onCreate(db);
    }

    //check session
    public Boolean checkSession (String sessionValues){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * From session Where login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    //update session
    public Boolean upgradeSession (String sessionValues, int id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = MyDB.update("session", contentValues, "id_session="+id, null);
        if (update == -1){
            return false;
        } else {
            return true;
        }
    }

    public Account checkUsername (String username){
        Account account= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * From admin where username = ?", new String[]{username});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setNama_admin(cursor.getString(1));
                account.setUsername(cursor.getString(2));
                account.setPassword(cursor.getString(3));
            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }


    public Account find (int id){
        Account account= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * From admin where id_admin = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setNama_admin(cursor.getString(1));
                account.setUsername(cursor.getString(2));
                account.setPassword(cursor.getString(3));
            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public Account login (String username, String password){
        Account account= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * From admin where username = ? and password =?", new String[]{username, password});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setNama_admin(cursor.getString(1));
                account.setUsername(cursor.getString(2));
                account.setPassword(cursor.getString(3));
            }
        }catch (Exception e){
         account = null;
        }
        return account;
    }

    public Boolean checkUsernamePassword (String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * From admin where username = ? and password =?", new String[]{username, password});
        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }
    public Cursor getData(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from admin where username = ?" + " and password=?", new String[] {username, password});
        return res;
    }
    //GET ADMIN
    public List<Account> allAdmin() {
        List<Account> accounts = new ArrayList<>();
        String selectQuery = "SELECT  * FROM admin";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account.setId(cursor.getInt(0));
                account.setNama_admin(cursor.getString(1));
                account.setUsername(cursor.getString(2));
                account.setPassword(cursor.getString(3));
                accounts.add(account);
            } while (cursor.moveToNext());
        }

        db.close();
        return accounts;
    }

    //DELETE ADMIN
    public Boolean deleteAdmin (long id){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            return MyDB.delete("admin",  "id_admin ="+id, null) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //INSERT ADMIN
    public Boolean insertAdmin (Account account){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama_admin", account.getNama_admin());
            contentValues.put("username", account.getUsername());
            contentValues.put("password", account.getPassword());
            result = MyDB.insert("admin", null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //UPDATE/EDIT ADMIN
    public Boolean updateAdmin (Account account){
        Boolean result= true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama_admin", account.getNama_admin());
            contentValues.put("username", account.getUsername());
            contentValues.put("password", account.getPassword());
            result = MyDB.update("admin", contentValues, "id_admin = ?", new String[]{String.valueOf(account.getId())}) >0;
        }catch (Exception e){
            result = null;
        }
        return result;
    }

    //-------------------------------------------------------------------------------------------------------------------PENYAKIT------------------------------------------------------------------------------------------
    //GET PENYAKIT
    public List<Penyakit> allPenyakit() {
        List<Penyakit> penyakits = new ArrayList<>();
        String selectQuery = "SELECT  * FROM penyakit";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Penyakit penyakit = new Penyakit();
                penyakit.setId_penyakit(cursor.getInt(0));
                penyakit.setNama_penyakit(cursor.getString(1));
                penyakit.setDeskripsi(cursor.getString(2));
                penyakit.setSaran(cursor.getString(3));
                penyakits.add(penyakit);
            } while (cursor.moveToNext());
        }

        db.close();
        return penyakits;
    }


    //cari Penyakit
    public Penyakit findPenyakit (long id){
        Penyakit penyakit= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * From penyakit where id_penyakit = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()){
                penyakit = new Penyakit();
                penyakit.setId_penyakit(cursor.getInt(0));
                penyakit.setNama_penyakit(cursor.getString(1));
                penyakit.setDeskripsi(cursor.getString(2));
                penyakit.setSaran(cursor.getString(3));
            }
        }catch (Exception e){
            penyakit = null;
        }
        return penyakit;
    }

    //Get Total Data Penyakit
    public int getPenyakitCount() {
        String countQuery = "SELECT  * FROM penyakit";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //DELETE PENYAKIT
    public Boolean deletePeyakit (long id){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            return MyDB.delete("penyakit",  "id_penyakit ="+id, null) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //INSERT PENYAKIT
    public Boolean insertPenyakit (Penyakit penyakit){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama_penyakit", penyakit.getNama_penyakit());
            contentValues.put("deskripsi", penyakit.getDeskripsi());
            contentValues.put("saran", penyakit.getSaran());
            result = MyDB.insert("penyakit", null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //UPDATE/EDIT PENYAKIT
    public Boolean updatePenyakit (Penyakit penyakit){
        Boolean result= true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama_penyakit", penyakit.getNama_penyakit());
            contentValues.put("deskripsi", penyakit.getDeskripsi());
            contentValues.put("saran", penyakit.getSaran());
            result = MyDB.update("penyakit", contentValues, "id_penyakit = ?", new String[]{String.valueOf(penyakit.getId_penyakit())}) >0;
        }catch (Exception e){
            result = null;
        }
        return result;
    }

//    -------------------------------------------------------------------------------------------------------------------PENYAKIT END------------------------------------------------------------------------------------------


//    -------------------------------------------------------------------------------------------------------------------GEJALA------------------------------------------------------------------------------------------

    //GET GEJALA
    public List<Gejala> allGejala() {
        List<Gejala> gejalas = new ArrayList<>();
        String selectQuery = "SELECT  * FROM gejala";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Gejala gejala = new Gejala();
                gejala.setId_gejala(cursor.getInt(0));
                gejala.setNama_gejala(cursor.getString(1));
                gejala.setPenyakit_terpilih(cursor.getString(2));
                gejalas.add(gejala);
            } while (cursor.moveToNext());
        }

        db.close();
        return gejalas;
    }

    //getNama Gejala
    public Cursor getNamaGejala(){
        String selectQuery = "SELECT  * FROM gejala";
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(selectQuery, null);
    }

    //CARI GEJALA
    public Gejala findGejala (long id){
        Gejala gejala= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * From gejala where id_gejala = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()){
                gejala = new Gejala();
                gejala.setId_gejala(cursor.getInt(0));
                gejala.setNama_gejala(cursor.getString(1));
                gejala.setPenyakit_terpilih(cursor.getString(2));
            }
        }catch (Exception e){
            gejala = null;
        }
        return gejala;
    }

    //Get Total Data GEJALA
    public int getGejalaCount() {
        String countQuery = "SELECT  * FROM gejala";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //DELETE GEJALA
    public Boolean deleteGejala (long id){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            return MyDB.delete("gejala",  "id_gejala ="+id, null) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //INSERT GEJALA
    public Boolean insertGejala (Gejala gejala){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama_gejala", gejala.getNama_gejala());
            result = MyDB.insert("gejala", null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //UPDATE/EDIT GEJALA
    public Boolean updateGejala (Gejala gejala){
        Boolean result= true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama_gejala", gejala.getNama_gejala());
            result = MyDB.update("gejala", contentValues, "id_gejala = ?", new String[]{String.valueOf(gejala.getId_gejala())}) >0;
        }catch (Exception e){
            result = null;
        }
        return result;
    }
//    -------------------------------------------------------------------------------------------------------------------GEJALA END------------------------------------------------------------------------------------------

    //    ---------------------------------------------------------------------------------------------------------------PEGETAHUAN------------------------------------------------------------------------------------------

    //GET GEJALA
    public List<Pengetahuan> allPengetahuan() {
        List<Pengetahuan> pengetahuans = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQueryPengetahuan = "SELECT " +
                "rule.id_rule AS rule_id_rule, " +
                "rule.probabilitas_mb, " +
                "rule.probabilitas_md, " +
                "rule.probabilitas_total, " +
                "rule.probabilitas_bayes, " +
                "rule.id_pen AS id_penyakit, " +
                "penyakit.nama_penyakit AS penyakit_nama_penyakit, " +
                "rule.id_gej AS id_gejala, " +
                "gejala.nama_gejala AS gejala_nama_gejala " +
                "FROM rule "+
                "LEFT JOIN penyakit ON rule.id_pen = penyakit.id_penyakit " +
                "LEFT JOIN gejala ON rule.id_gej = gejala.id_gejala";

        Cursor cursor = db.rawQuery(selectQueryPengetahuan, null);
        if (cursor.moveToFirst()){
            do {
                Pengetahuan pengetahuan = new Pengetahuan();
                pengetahuan.setId_pengetahuan(cursor.getInt(0));
                pengetahuan.setProbabilitas_mb(cursor.getDouble(1));
                pengetahuan.setProbabilitas_md(cursor.getDouble(2));
                pengetahuan.setProbabilitas_total(cursor.getDouble(3));
                pengetahuan.setProbabilitas_bayes(cursor.getDouble(4));
                pengetahuan.setId_penyakit(cursor.getInt(5));
                pengetahuan.setNama_penyakit(cursor.getString(6));
                pengetahuan.setId_gejala(cursor.getInt(7));
                pengetahuan.setNama_gejala(cursor.getString(8));
                pengetahuans.add(pengetahuan);
            }while (cursor.moveToNext());
        }
        db.close();
        return pengetahuans;
    }


    //CARI PENGETAHUAN
    public Pengetahuan findPengetahuan (long id){
        Pengetahuan pengetahuan= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("SELECT " +
                    "rule.id_rule AS rule_id_rule, " +
                    "rule.probabilitas_mb, " +
                    "rule.probabilitas_md, " +
                    "rule.probabilitas_total, " +
                    "rule.probabilitas_bayes, " +
                    "rule.id_pen AS id_penyakit, " +
                    "penyakit.nama_penyakit AS penyakit_nama_penyakit, " +
                    "rule.id_gej AS id_gejala, " +
                    "gejala.nama_gejala AS gejala_nama_gejala " +
                    "FROM rule "+
                    "LEFT JOIN penyakit ON rule.id_pen = penyakit.id_penyakit " +
                    "LEFT JOIN gejala ON rule.id_gej = gejala.id_gejala " +
                    "WHERE id_rule = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()){
                pengetahuan = new Pengetahuan();
                pengetahuan.setId_pengetahuan(cursor.getInt(0));
                pengetahuan.setProbabilitas_mb(cursor.getDouble(1));
                pengetahuan.setProbabilitas_md(cursor.getDouble(2));
                pengetahuan.setProbabilitas_total(cursor.getDouble(3));
                pengetahuan.setProbabilitas_bayes(cursor.getDouble(4));
                pengetahuan.setId_penyakit(cursor.getInt(5));
                pengetahuan.setNama_penyakit(cursor.getString(6));
                pengetahuan.setId_gejala(cursor.getInt(7));
                pengetahuan.setNama_gejala(cursor.getString(8));
            }while (cursor.moveToNext());
        }catch (Exception e){
            pengetahuan = null;
        }
        return pengetahuan;
    }

    //Get Total Data GEJALA
    public int getPengetahuanCount() {
        String countQuery = "SELECT  * FROM rule";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //DELETE GEJALA
    public Boolean deletePengetahuan (long id){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            return MyDB.delete("rule",  "id_rule ="+id, null) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //INSERT GEJALA
    public Boolean insertPengetahuan (Pengetahuan pengetahuan){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_pen", pengetahuan.getId_penyakit());
            contentValues.put("id_gej", pengetahuan.getId_gejala());
            contentValues.put("probabilitas_mb", pengetahuan.getProbabilitas_mb());
            contentValues.put("probabilitas_md", pengetahuan.getProbabilitas_md());
            result = MyDB.insert("rule", null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //UPDATE/EDIT GEJALA
    public Boolean updatePengetahuan (Pengetahuan pengetahuan){
        Boolean result= true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id_pen", pengetahuan.getId_penyakit());
            contentValues.put("id_gej", pengetahuan.getId_gejala());
            contentValues.put("probabilitas_mb", pengetahuan.getProbabilitas_mb());
            contentValues.put("probabilitas_md", pengetahuan.getProbabilitas_md());
            result = MyDB.update("rule", contentValues, "id_rule = ?", new String[]{String.valueOf(pengetahuan.getId_pengetahuan())}) >0;
        }catch (Exception e){
            result = null;
        }
        return result;
    }
//    -------------------------------------------------------------------------------------------------------------------PENGETAHUAN END------------------------------------------------------------------------------------------


//    -------------------------------------------------------------------------------------------------------------------RIWAYAT START------------------------------------------------------------------------------------------

    //GET RIWAYAT
    public List<Riwayat> allRiwayat() {
        List<Riwayat> riwayats = new ArrayList<>();
        String selectQuery = "SELECT  * FROM riwayat";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Riwayat riwayat = new Riwayat();
                riwayat.setId_riwayat(cursor.getInt(0));
                riwayat.setTanggal(cursor.getString(1));
                riwayat.setRiwayat_nama_penyakit(cursor.getString(2));
                riwayat.setRiwayat_nilai(cursor.getString(3));
                riwayat.setRiwayat_nama_gejala(cursor.getString(4));
                riwayats.add(riwayat);
            } while (cursor.moveToNext());
        }

        db.close();
        return riwayats;
    }

    //getNama Gejala
    public Cursor getNamaRiwayat(){
        String selectQuery = "SELECT  * FROM riwayat";
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(selectQuery, null);
    }

    //INSERT GEJALA
    public Boolean insertRiwayat (Riwayat riwayat){
        boolean result = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("tanggal", riwayat.getTanggal());
            contentValues.put("riwayat_nama_penyakit", riwayat.getRiwayat_nama_penyakit());
            contentValues.put("riwayat_nilai", riwayat.getRiwayat_nilai());
            contentValues.put("riwayat_nama_gejala", riwayat.getRiwayat_nama_gejala());
            result = MyDB.insert("riwayat", null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    //CARI GEJALA
    public Riwayat findRiwayat (long id){
        Riwayat riwayat= null;
        try {
            SQLiteDatabase MyDB = this.getReadableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * From riwayat where id_riwayat = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()){
                riwayat = new Riwayat();
                riwayat.setId_riwayat(cursor.getInt(0));
                riwayat.setTanggal(cursor.getString(1));
                riwayat.setRiwayat_nama_penyakit(cursor.getString(2));
                riwayat.setRiwayat_nilai(cursor.getString(3));
                riwayat.setRiwayat_nama_gejala(cursor.getString(4));
            }
        }catch (Exception e){
            riwayat = null;
        }
        return riwayat;
    }


//    -------------------------------------------------------------------------------------------------------------------RIWAYAT END------------------------------------------------------------------------------------------
}
