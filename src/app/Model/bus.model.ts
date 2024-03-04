export class Bus{
    constructor(
        public busId:number,
        public busNumber:string,
        public capacity:number,
        public sourceCity:string='',
        public destinationCity:string,
        public distanceInKms:number,
        public durationInHours:number,
        public departureTime:string,
        public fare:number,
        public date:Date,
        public totalSeats:number,




    ){}}
