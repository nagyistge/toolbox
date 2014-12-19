package eu.amidst.core.utils;

import eu.amidst.core.database.*;
import eu.amidst.core.database.filereaders.DynamicDataOnDiskFromFile;
import eu.amidst.core.database.filereaders.StaticDataOnDiskFromFile;
import eu.amidst.core.database.filereaders.arffWekaReader.WekaDataFileReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by andresmasegosa on 10/12/14.
 */
public class ReservoirSampling {

    public static DataOnMemory samplingNumberOfSamples(int numberOfSamples, DataBase dataBase){

        Random random = new Random(0);
        DataOnMemoryListContainer dataOnMemoryList = new DataOnMemoryListContainer(dataBase.getAttributes());
        int count = 0;

        for (DataInstance instance : dataBase){
            if (count<numberOfSamples)
                dataOnMemoryList.add(count,instance);
            else{
                int r = random.nextInt(count);
                if (r < numberOfSamples)
                    dataOnMemoryList.add(r,instance);
            }
            count++;
        }
        return dataOnMemoryList;
    }

    public static DataOnMemory samplingNumberOfGBs(double numberOfGB, DataBase dataOnStream) {
        double numberOfBytesPerSample = dataOnStream.getAttributes().getList().size()*8.0;

        //We assume an overhead of 10%.
        int numberOfSamples = (int) ((1-0.1)*numberOfGB*1073741824.0/numberOfBytesPerSample);

        return samplingNumberOfSamples(numberOfSamples,dataOnStream);
    }

    public static void main(String[] args) throws Exception {
        DataOnDisk data = new StaticDataOnDiskFromFile(new WekaDataFileReader("datasets/syntheticDataCajaMar.arff"));

        DataOnMemory dataOnMemory = ReservoirSampling.samplingNumberOfSamples(1000, data);



    }


    }