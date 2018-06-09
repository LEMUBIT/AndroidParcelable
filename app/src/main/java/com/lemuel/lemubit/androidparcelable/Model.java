/*
One very important thing to pay close attention to is the order that you write and
read your values to and from the Parcel. They need to match up in both cases.
In my example, I write the String and then the int to the Parcel.
Afterwards, I read them in that same exact order.
The mechanism that Android uses to read the Parcel is blind and completely trusts you to get the order correct,
or else you will run into run-time crashes.

Another problem I have encountered is with ClassNotFound exceptions.
This is an issue with the Classloader not finding your class. To fix this you can manually set the Classloader to use.
If nothing is set, then it will try the default Classloader which leads to the exception.
*/
package com.lemuel.lemubit.androidparcelable;

import android.os.Parcel;
import android.os.Parcelable;


public class Model implements Parcelable {
    String mUserName;
    int mAge;
    Model mModel;

    // This is where you write the values you want to save to the `Parcel`.
    // The `Parcel` class has methods defined to help you save all of your values.
    // Note that there are only methods defined for simple values, lists, and other Parcelable objects.
    // You may need to make several classes Parcelable to send the data you want.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserName);
        dest.writeInt(mAge);
        dest.writeParcelable(mModel, flags);
    }

    // Using the `in` variable, we can retrieve the values that
    // we originally wrote into the `Parcel`.  This constructor is usually
    // private so that only the `CREATOR` field can access.
    protected Model(Parcel in) {
        mUserName = in.readString();
        mAge = in.readInt();
        mModel = in.readParcelable(Model.class.getClassLoader());
    }


    public Model() {
        // Normal actions performed by class, since this is still a normal object!
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Creator<Model> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Creator<Model> CREATOR = new Creator<Model>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!

        /* In few words, "marshalling" refers to the process of converting the data or the objects
        into a byte-stream, and "unmarshalling" is the reverse process of converting the byte-stream back
        to their original data or object.*/
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
