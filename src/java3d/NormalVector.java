package java3d;

public enum NormalVector {

    NORMALPOSX(0),

    NORMALPOSY(1),

    NORMALPOSZ(2),

    NORMALNEGX(3),

    NORMALNEGY(4),

    NORMALNEGZ(5);

    protected int i;

    NormalVector(int i){
        this.i = i;
    }

    public Vector3D get(){
        return NormalVectors.NORMALVECTORS[this.i];
    }

    }
