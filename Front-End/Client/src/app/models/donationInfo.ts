export class DonationInfo {
    id: number;
    title: string;
    message: string;
    montant: string;
    user: any;

    static dataToDisplay(donationInfo) {
        if (donationInfo) {
        const resultats = new Array(new DonationInfo());
        donationInfo.forEach(el => {
            const resultat = new DonationInfo();
            resultat.id = el.id;
            resultat.title = el.title;
            resultat.message = el.message;
            resultat.montant = el.montant;
            resultat.user = el.user;
            resultats.push(resultat);
        });
        resultats.splice(0, 1);
        return resultats;
        }
    }
}
