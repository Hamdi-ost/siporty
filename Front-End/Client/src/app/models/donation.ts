export class Donation {
    id: number;
    montant: string;
    date: Date;
    donationInfo: any;

    static dataToDisplay(donation) {
        if (donation) {
        const resultats = new Array(new Donation());
        donation.forEach(el => {
            const resultat = new Donation();
            resultat.id = el.id;
            resultat.donationInfo = el.donationInfo;
            resultat.date = el.date;
            resultat.montant = el.montant;
            resultats.push(resultat);
        });
        resultats.splice(0, 1);
        return resultats;
        }
    }
}
