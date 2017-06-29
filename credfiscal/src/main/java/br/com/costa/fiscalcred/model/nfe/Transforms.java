package br.com.costa.fiscalcred.model.nfe;

public class Transforms {
	private Transform[] Transform;

	public Transform[] getTransform() {
		return Transform;
	}

	public void setTransform(Transform[] Transform) {
		this.Transform = Transform;
	}

	@Override
	public String toString() {
		return "ClassPojo [Transform = " + Transform + "]";
	}
}
