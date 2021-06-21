import { Datoteka } from './datoteka.model';
import { Folder } from "./folder.model";

export interface Drive {
  folderi?: Folder[];
  datoteke?: Datoteka[];

}
