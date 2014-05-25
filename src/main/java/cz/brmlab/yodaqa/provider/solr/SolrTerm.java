package cz.brmlab.yodaqa.provider.solr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cz.brmlab.yodaqa.model.Question.Clue;
import cz.brmlab.yodaqa.model.Question.CluePhrase;

public class SolrTerm {
	protected String termStr;
	protected double weight;

	public SolrTerm(String termStr_, double weight_) {
		termStr = termStr_;
		weight = weight_;
	}

	/**
	 * @return the termStr
	 */
	public String getTermStr() {
		return termStr;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/** Convert Clue annotations to SolrTerm objects. Ignores some
	 * clues that aren't very good as keywords. */
	public static List<SolrTerm> cluesToTerms(Collection<Clue> clues) {
		List<SolrTerm> terms = new ArrayList<SolrTerm>(clues.size());
		for (Clue clue : clues) {
			// phrases suck as search keywords
			if (clue instanceof CluePhrase)
				continue;

			String keyterm = clue.getCoveredText();
			Double weight = clue.getWeight();
			SolrTerm term = new SolrTerm(keyterm, weight);
			terms.add(term);
		}

		return terms;
	}
}
