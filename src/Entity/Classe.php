<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Classe
 *
 * @ORM\Table(name="classe", uniqueConstraints={@ORM\UniqueConstraint(name="nomClasse", columns={"nomClasse"})})
 * @ORM\Entity
 */
class Classe
{
    /**
     * @var int
     *
     * @ORM\Column(name="idClasse", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idclasse;

    /**
     * @var string
     *
     * @ORM\Column(name="nomClasse", type="string", length=11, nullable=false)
     * @Assert\Length(
     *      min = 3,
     *      max = 10,
     *      minMessage = "Le nom du classe doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "Le nom d'un article doit comporter au plus {{ limit }} caractères"
     * )
     */
    private $nomclasse;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 15,
     *      max = 25,
     *      notInRangeMessage = "le nombre d'éleve doit étre compris entre {{ min }} et {{ max }} éleves",
     * )
     */
    private $nbr;

    /**
     * @var int
     *
     * @ORM\Column(name="niveau", type="integer", nullable=false)
     */
    private $niveau;

    /**
     * @return int
     */
    public function getIdclasse(): int
    {
        return $this->idclasse;
    }

    /**
     * @param int $idclasse
     */
    public function setIdclasse(int $idclasse): void
    {
        $this->idclasse = $idclasse;
    }

    /**
     * @return string|null
     */
    public function getNomclasse(): ?string
    {
        return $this->nomclasse;
    }

    /**
     * @param string $nomclasse
     */
    public function setNomclasse(string $nomclasse): void
    {
        $this->nomclasse = $nomclasse;
    }

    /**
     * @return int|null
     */
    public function getNbr(): ?int
    {
        return $this->nbr;
    }

    /**
     * @param int $nbr
     */
    public function setNbr(int $nbr): void
    {
        $this->nbr = $nbr;
    }

    /**
     * @return int|null
     */
    public function getNiveau(): ?int
    {
        return $this->niveau;
    }

    /**
     * @param int $niveau
     */
    public function setNiveau(int $niveau): void
    {
        $this->niveau = $niveau;
    }

    public function __toString(): string
    {
        // TODO: Implement __toString() method.
        return $this->nomclasse;
    }


}
