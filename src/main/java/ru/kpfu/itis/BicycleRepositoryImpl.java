package ru.kpfu.itis;

import com.google.appengine.api.search.query.QueryParser;
import com.googlecode.luceneappengine.GaeDirectory;
import com.googlecode.luceneappengine.GaeLuceneUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Repository;

import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * Created by eljah32 on 6/6/2016.
 */
@Repository
public class BicycleRepositoryImpl implements BicycleRepository {

    private static final Logger log = Logger.getLogger(BicycleRepositoryImpl.class.getName());

    @Override
    public List<Bicycle> findBySerialNumber(String name) {


        StandardAnalyzer analyzer = new StandardAnalyzer();
        org.apache.lucene.search.Query q = null;
        try {
            q = new org.apache.lucene.queryparser.classic.QueryParser("serialNumber", analyzer).parse(name);
        } catch (ParseException e) {
            log.severe("Search query is not parceble "+name);
            e.printStackTrace();
        }

        int hitsPerPage = 10;
        GaeDirectory index = new GaeDirectory();//create a default index
        IndexReader reader = null;

        List<Bicycle> bicyclesToReturn=new ArrayList<Bicycle>() {
        };

        try {
            reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(q, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;
            for(int i=0;i<hits.length;++i) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                String idSt=d.get("id");
                log.info("Finding item with id ="+idSt);
                Bicycle b=findOne(Long.parseLong(idSt));
                bicyclesToReturn.add(b);
                //System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bicyclesToReturn;
    }

    @Override
    public List<Bicycle> findAll() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(Bicycle.class);
        //q.setFilter("lastName == lastNameParam");
        q.setOrdering("serialNumber desc");
        //q.declareParameters("String lastNameParam");
        List<Bicycle> results = null;
        try {
            results = (List<Bicycle>) q.execute();
            if (!results.isEmpty()) {
            //    for (Person p : results) {
            //        // Process result p
            //    }
            } else {
                // Handle "no results" case
            }
        } finally {
            q.closeAll();
            return results;
        }

    }

    @Override
    public void save(Bicycle bicycle) {
        PersistenceManager pm = PMF.get().getPersistenceManager();


        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        GaeDirectory directory = new GaeDirectory();//create a default index
        //IndexWriterConfig config = GaeLuceneUtil.getIndexWriterConfig(analyzer);//get configuration if you are using a LAE version less than 3.x.x add LUCENE_VERSION as parameter
        try {
            pm.makePersistent(bicycle);
        } finally {
            pm.close();
            try {
                IndexWriter w = new IndexWriter(directory, config);//get the writer
                addDoc(w, bicycle.getSerialNumber(), bicycle.getId());
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Bicycle findOne(long id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Bicycle returnableBicycle=null;
        try {
//            returnableBicycle=pm.getObjectById(Bicycle.class, id);
            Query q = pm.newQuery(Bicycle.class);
            q.setFilter("id == "+id);
            q.setOrdering("serialNumber desc");

            try {

                returnableBicycle = ((List<Bicycle>) q.execute()).get(0);

            }
            catch (java.lang.IndexOutOfBoundsException e)
            {
                log.severe("No entry with id "+id);
            }
            finally {
                q.closeAll();

            }

        } finally {
            pm.close();
        }
        return returnableBicycle;
    }

    private static void addDoc(IndexWriter w, String serialNumber, long id) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("serialNumber", serialNumber, Field.Store.YES));
        doc.add(new LongField("id", id, Field.Store.YES));
        w.addDocument(doc);
    }
}
